package com.example.english_learning_server.reponsitory;

import com.example.english_learning_server.entity.Test;
import com.example.english_learning_server.entity.User;
import com.example.english_learning_server.entity.UserTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserTestRepository extends JpaRepository<UserTest, Long> {
    Optional<UserTest> findByIdAndUserId(Long id, Integer userId);

    @Query(value = "WITH RankedTests AS (" +
            "  SELECT " +
            "    t.test_id, " +
            "    t.description, " +
            "    t.exam_date, " +
            "    t.exam_time, " +
            "    t.level, " +
            "    t.max_number_of_exams, " +
            "    COALESCE(ut.status, 0) AS user_test_status, " +
            "    t.test_name, " +
            "    t.type, " +
            "    t.course_id, " +
            "    COALESCE(ut.score, '0') AS score, " +
            "    COALESCE(ut.date, NULL) AS date, " +
            "    COALESCE(ut.exam_times, '00:00:00') AS exam_times, " +
            "    ROW_NUMBER() OVER (" +
            "      PARTITION BY t.test_id " +
            "      ORDER BY ut.date DESC, ut.exam_times DESC" +
            "    ) AS rn " +
            "  FROM " +
            "    test t " +
            "  LEFT JOIN " +
            "    user_test ut " +
            "  ON " +
            "    t.test_id = ut.test_id AND ut.user_id = :userId " +
            "  WHERE " +
            "    t.course_id = :courseId" +
            ") " +  // Xóa dấu phẩy ở đây
            "SELECT " +
            "  test_id, " +
            "  test_name, " +
            "  description, " +
            "  exam_date, " +
            "  exam_time, " +
            "  level, " +
            "  max_number_of_exams, " +
            "  user_test_status, " +
            "  type, " +
            "  course_id, " +
            "  score, " +
            "  date, " +
            "  exam_times " +
            "FROM " +
            "  RankedTests " +
            "WHERE " +
            "  rn = 1 " +
            "ORDER BY " +
            "  test_id, course_id",
            nativeQuery = true)
    List<Object[]> getRankedTests(@Param("userId") Integer userId, @Param("courseId") Integer courseId);

    @Query(value = "SELECT " +
            "t.test_id, " +
//            "t.description, " +
            "t.exam_time, " +
            "t.level, " +
            "t.max_number_of_exams, " +
            "ut.status AS user_test_status, " +
            "t.test_name, " +
            "t.type, " +
            "t.course_id, " +
            "ut.score, " +
            "ut.date, " +
            "ut.exam_times " +
            "FROM " +
            "test t " +
            "LEFT JOIN user_test ut " +
            "ON t.test_id = ut.test_id " +
            "WHERE ut.user_id = :userId AND t.course_id = :courseId " +
            "ORDER BY ut.date DESC, ut.exam_times DESC, t.test_id, t.course_id",
            nativeQuery = true)
    List<Object[]> getRankedTests2(@Param("userId") Integer userId, @Param("courseId") Integer courseId);

}
