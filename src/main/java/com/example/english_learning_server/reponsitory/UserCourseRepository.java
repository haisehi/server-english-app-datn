package com.example.english_learning_server.reponsitory;

import com.example.english_learning_server.entity.UserCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCourseRepository extends JpaRepository<UserCourse, Long> {

    // Phương thức tìm kiếm UserCourses theo userId
    List<UserCourse> findByUserId(Integer userId);
}
