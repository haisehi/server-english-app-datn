package com.example.english_learning_server.controller;

import com.example.english_learning_server.dto.UserCourseDTO;
import com.example.english_learning_server.entity.UserCourse;
import com.example.english_learning_server.service.UserCourseService;
import com.example.english_learning_server.user.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/user-courses")
public class UserCourseController {

    @Autowired
    private UserCourseService userCourseService;

    // Lấy UserCourses của user hiện tại
    @GetMapping("/me")
    public List<UserCourseDTO> getUserCoursesForCurrentUser() {
        return userCourseService.getUserCoursesForCurrentUser();
    }

    // Đăng ký học khóa học
    @PostMapping("/enroll")
    public UserCourseDTO enrollInCourse(@RequestBody Map<String, Object> request) {
        Integer courseId = (Integer) request.get("courseId");
        Integer studentCode = (Integer) request.get("studentCode");
        Role role = Role.valueOf((String) request.get("role")); // Chuyển đổi string sang enum

        // Gọi service để đăng ký khóa học (không cần truyền userId nữa)
        return userCourseService.enrollInCourse(courseId, studentCode, role);
    }



    // Lấy tất cả UserCourses
    @GetMapping
    public List<UserCourseDTO> getAllUserCourses() {
        return userCourseService.getAllUserCourses();
    }

    // Lấy UserCourse theo id
    @GetMapping("/{id}")
    public UserCourseDTO getUserCourseById(@PathVariable Long id) {
        return userCourseService.getUserCourseById(id);
    }


    // Xóa UserCourse theo id
    @DeleteMapping("/{id}")
    public void deleteUserCourse(@PathVariable Long id) {
        userCourseService.deleteUserCourse(id);
    }
}
