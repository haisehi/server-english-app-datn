package com.example.english_learning_server.reponsitory;

import com.example.english_learning_server.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<Test, Integer> {
    // Tìm danh sách bài kiểm tra theo ID của khóa học
    List<Test> findByCourse_CourseId(Integer courseId);
}
