package com.example.english_learning_server.reponsitory;

import com.example.english_learning_server.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    @Query("SELECT c, " +
            "CASE WHEN uc.user.id IS NOT NULL THEN 'ENROLLED' ELSE 'NOT_ENROLLED' END AS enrollmentStatus " +
            "FROM Course c " +
            "LEFT JOIN UserCourse uc ON c.id = uc.course.id AND uc.user.id = :userId " +
            "ORDER BY c.id ASC")
    List<Object[]> findCoursesWithEnrollmentStatus(@Param("userId") Integer userId);
}