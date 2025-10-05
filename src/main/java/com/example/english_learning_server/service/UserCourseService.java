package com.example.english_learning_server.service;

import com.example.english_learning_server.converter.UserCourseMapper;
import com.example.english_learning_server.dto.UserCourseDTO;
import com.example.english_learning_server.entity.UserCourse;
import com.example.english_learning_server.reponsitory.CourseRepository;
import com.example.english_learning_server.reponsitory.UserCourseRepository;
import com.example.english_learning_server.reponsitory.UserReponsitory;
import com.example.english_learning_server.entity.Course;
import com.example.english_learning_server.entity.User;
import com.example.english_learning_server.user.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserCourseService {

    @Autowired
    private UserReponsitory userRepository;

    @Autowired
    private UserCourseRepository userCourseRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserCourseMapper userCourseMapper;

    public UserCourseDTO enrollInCourse(Integer courseId, Integer studentCode, Role role) {
        // Lấy email của user từ SecurityContext
        String userEmail = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userEmail = ((UserDetails) principal).getUsername();
        } else {
            userEmail = principal.toString();
        }

        // Tìm user hiện tại dựa trên email
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Tìm course theo courseId
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        // Tạo UserCourse mới
        UserCourse userCourse = UserCourse.builder()
                .user(user)
                .course(course)
                .studentCode(studentCode)
                .role(role)
                .status(1) // Đặt trạng thái mặc định
                .build();

        // Lưu UserCourse
        UserCourse savedUserCourse = userCourseRepository.save(userCourse);

        // Trả về DTO
        return userCourseMapper.toDto(savedUserCourse);
    }


    public List<UserCourseDTO> getAllUserCourses() {
        List<UserCourse> userCourses = userCourseRepository.findAll();
        return userCourses.stream()
                .map(userCourseMapper::toDto)
                .collect(Collectors.toList());
    }

    public UserCourseDTO getUserCourseById(Long id) {
        UserCourse userCourse = userCourseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserCourse not found"));
        return userCourseMapper.toDto(userCourse);
    }

    public List<UserCourseDTO> getUserCoursesByUserId(Integer userId) {
        List<UserCourse> userCourses = userCourseRepository.findByUserId(userId);
        return userCourses.stream()
                .map(userCourseMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<UserCourseDTO> getUserCoursesForCurrentUser() {
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

        // Lấy danh sách UserCourse của user và chuyển đổi sang DTO
        List<UserCourse> userCourses = userCourseRepository.findByUserId(user.getId());
        return userCourses.stream()
                .map(userCourseMapper::toDto)
                .collect(Collectors.toList());
    }

    public void deleteUserCourse(Long id) {
        UserCourse userCourse = userCourseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserCourse not found"));
        userCourseRepository.delete(userCourse);
    }
}