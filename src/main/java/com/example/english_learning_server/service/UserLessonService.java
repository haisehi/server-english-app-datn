package com.example.english_learning_server.service;

import com.example.english_learning_server.converter.UserLessonMapper;
import com.example.english_learning_server.dto.UserLessonDTO;
import com.example.english_learning_server.entity.Course;
import com.example.english_learning_server.entity.Lesson;
import com.example.english_learning_server.entity.User;
import com.example.english_learning_server.entity.UserLesson;
import com.example.english_learning_server.reponsitory.CourseRepository;
import com.example.english_learning_server.reponsitory.LessonRepository;
import com.example.english_learning_server.reponsitory.UserLessonRepository;
import com.example.english_learning_server.reponsitory.UserReponsitory;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserLessonService {

    @Autowired
    private UserLessonRepository userLessonRepository;

    @Autowired
    private UserReponsitory userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private LessonRepository lessonRepository;


    @Autowired
    private UserLessonMapper userLessonMapper;

    // Lấy danh sách tất cả UserLesson
    public List<UserLesson> getAllUserLessons() {
        return userLessonRepository.findAll();
    }

    // Lấy danh sách UserLesson theo userId
    public List<UserLesson> getUserLessonsByUserId(Integer userId) {
        return userLessonRepository.findByUserId(userId);
    }

    // Xóa UserLesson theo id
    public void deleteUserLesson(Long id) {
        Optional<UserLesson> userLesson = userLessonRepository.findById(id);
        if (userLesson.isPresent()) {
            userLessonRepository.deleteById(id);
        } else {
            throw new RuntimeException("UserLesson not found with id " + id);
        }
    }


    // Lấy danh sách UserLessons của user hiện tại và sắp xếp theo level
    public List<UserLessonDTO> getUserLessonsForCurrentUser() {
        // Lấy email của user từ SecurityContext
        String userEmail = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userEmail = ((UserDetails) principal).getUsername();
        } else {
            userEmail = principal.toString();
        }

        // Tìm user hiện tại
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Lấy danh sách UserLesson của user
        List<UserLesson> userLessons = userLessonRepository.findByUserId(user.getId());

        // Chuyển đổi danh sách UserLesson sang UserLessonDTO và sắp xếp theo level
        return userLessons.stream()
                .map(userLesson -> {
                    UserLessonDTO dto = new UserLessonDTO();
                    dto.setLessonId(userLesson.getLesson().getLessonId());
                    dto.setLessonName(userLesson.getLesson().getLessonName());
                    dto.setContent(userLesson.getLesson().getContent());
                    dto.setAttachments(userLesson.getLesson().getAttachments());
                    dto.setLevel(userLesson.getLesson().getLevel());
                    dto.setCourseId(userLesson.getCourse().getCourseId());

                    // Kiểm tra nếu progress là null thì gán là 0
                    Double progress = userLesson.getProgress();
                    dto.setProgress(progress != null ? progress : 0.0);  // Nếu progress null thì gán 0.0

                    return dto;
                })
                .sorted(Comparator.comparing(UserLessonDTO::getLevel))  // Sắp xếp theo level
                .collect(Collectors.toList());
    }


    // bắt đầu một bài học
    public UserLesson startLesson(Integer userId, Integer courseId, Integer lessonId, Double progress) {
        // Tìm User, Course và Lesson theo ID
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(() -> new RuntimeException("Lesson not found"));

        // Tạo một đối tượng UserLesson mới và lưu vào bảng
        UserLesson userLesson = new UserLesson();
        userLesson.setUser(user);
        userLesson.setCourse(course);
        userLesson.setLesson(lesson);
        userLesson.setProgress(progress); // Dùng tham số progress truyền vào
        userLesson.setStatus(1); // Trạng thái khóa học mở (có thể là 1 cho "đang học")

        // Lưu vào bảng UserLesson
        return userLessonRepository.save(userLesson);
    }



    // Cập nhật UserLesson
    public UserLessonDTO updateUserLesson(Long id, UserLessonDTO userLessonDTO) {
        // Tìm UserLesson theo ID
        UserLesson existingUserLesson = userLessonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserLesson not found"));

        // Cập nhật các trường thông tin
        if (userLessonDTO.getProgress() > 0) {
            existingUserLesson.setProgress(userLessonDTO.getProgress());
        }

        // Lưu lại thay đổi vào database
        UserLesson updatedUserLesson = userLessonRepository.save(existingUserLesson);

        // Trả về UserLessonDTO đã được cập nhật
        return userLessonMapper.toDTO(updatedUserLesson);
    }

    // cập nhật progress
    @Transactional
    public void updateUserLessonProgress(Integer userId, Integer courseId, Integer lessonId, double progress) {
        userLessonRepository.updateProgress(progress, lessonId, userId, courseId);
    }

    // thêm hoặc cập nhật
    public UserLesson startOrUpdateLesson(Integer userId, Integer courseId, Integer lessonId, Double progress) {
        // Kiểm tra xem UserLesson đã tồn tại chưa
        List<UserLesson> userLessons = userLessonRepository.findByUserId(userId);
        Optional<UserLesson> existingUserLesson = userLessons.stream()
                .filter(ul -> ul.getCourse().getCourseId().equals(courseId) && ul.getLesson().getLessonId().equals(lessonId))
                .findFirst();

        if (existingUserLesson.isPresent()) {
            // Nếu đã có UserLesson, cập nhật tiến trình
            UserLesson userLesson = existingUserLesson.get();
            userLesson.setProgress(progress);
            return userLessonRepository.save(userLesson);
        } else {
            // Nếu chưa có UserLesson, tạo mới
            return startLesson(userId, courseId, lessonId, progress); // Gọi lại phương thức startLesson để tạo mới UserLesson
        }
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Phương thức lấy tiến trình của người dùng hiện tại
    public double getProgressForCurrentUser(Integer courseId, Integer lessonId) {
        // Lấy email của user từ SecurityContext
        String userEmail = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userEmail = ((UserDetails) principal).getUsername();
        } else {
            userEmail = principal.toString();
        }

        // Tìm user hiện tại trong database
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Truy vấn tiến trình cho bài học (lesson) và khóa học (course) của user hiện tại
        Optional<UserLesson> userLesson = userLessonRepository.findProgressByUserIdCourseIdLessonId(user.getId(), courseId, lessonId);

        // Trả về tiến trình (progress), nếu không có thì trả về 0
        return userLesson.map(UserLesson::getProgress).orElse(0.0);
    }
}