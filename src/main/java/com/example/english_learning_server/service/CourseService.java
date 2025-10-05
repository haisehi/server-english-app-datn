package com.example.english_learning_server.service;

import com.example.english_learning_server.converter.CourseMapper;
import com.example.english_learning_server.converter.UserCourseMapper;
import com.example.english_learning_server.dto.CourseDTO;
import com.example.english_learning_server.dto.UserCourseDTO;
import com.example.english_learning_server.entity.Course;
import com.example.english_learning_server.entity.User;
import com.example.english_learning_server.reponsitory.CourseRepository;
import com.example.english_learning_server.reponsitory.UserReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {

    @Autowired
    private UserReponsitory userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserCourseMapper userCourseMapper;


    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseById(Integer id) {
        return courseRepository.findById(id);
    }

    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course updateCourse(Integer id, Course courseDetails) {
        return courseRepository.findById(id).map(course -> {
            course.setCourseName(courseDetails.getCourseName());
            course.setCourseCode(courseDetails.getCourseCode());
            course.setMaxQuantity(courseDetails.getMaxQuantity());
            course.setStatusCourse(courseDetails.getStatusCourse());
            course.setImage(courseDetails.getImage());
            return courseRepository.save(course);
        }).orElse(null);
    }

    public boolean deleteCourse(Integer id) {
        return courseRepository.findById(id).map(course -> {
            courseRepository.delete(course);
            return true;
        }).orElse(false);
    }

    public List<UserCourseDTO> getCoursesForCurrentUser() {
        String userEmail = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userEmail = ((UserDetails) principal).getUsername();
        } else {
            userEmail = principal.toString();
        }

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Lấy danh sách các khóa học và trạng thái đăng ký của user
        List<Object[]> coursesWithStatus = courseRepository.findCoursesWithEnrollmentStatus(user.getId());

        // Chuyển đổi kết quả sang DTO
        return coursesWithStatus.stream()
                .map(result -> {
                    Course course = (Course) result[0];  // Lấy khóa học
                    String enrollmentStatus = (String) result[1];  // Lấy trạng thái đăng ký
                    UserCourseDTO dto = new UserCourseDTO();
                    dto.setCourseId(course.getCourseId());
                    dto.setCourseName(course.getCourseName());
                    dto.setCourseCode(course.getCourseCode());
                    dto.setStatusCourse(course.getStatusCourse());  // Trường này chỉ cần nếu có
                    dto.setMaxQuantity(course.getMaxQuantity());
                    dto.setImage(course.getImage());
                    dto.setEnrollmentStatus(enrollmentStatus); // Gắn trạng thái đăng ký

                    return dto;
                })
                .collect(Collectors.toList());
    }

}