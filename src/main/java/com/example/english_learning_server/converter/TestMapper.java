package com.example.english_learning_server.converter;

import com.example.english_learning_server.dto.TestDTO;
import com.example.english_learning_server.entity.Course;
import com.example.english_learning_server.entity.Test;
import org.springframework.stereotype.Component;

@Component
public class TestMapper {

    public TestDTO toDTO(Test test) {
        TestDTO dto = new TestDTO();
        dto.setTestId(test.getTestId());
        dto.setTestName(test.getTestName());
        dto.setDescription(test.getDescription());
        dto.setExamTime(test.getExamTime());  // Cập nhật ánh xạ examTime
        dto.setExamDate(test.getExamDate());
        dto.setType(test.getType());
        dto.setMaxNumberOfExams(test.getMaxNumberOfExams());
        dto.setLevel(test.getLevel());
        dto.setPassingScore(test.getPassingScore());
        dto.setStatus(test.getStatus());
        dto.setCourseId(test.getCourse() != null ? test.getCourse().getCourseId() : null);
        return dto;
    }

    public Test toEntity(TestDTO dto) {
        Test test = new Test();
        test.setTestId(dto.getTestId());
        test.setTestName(dto.getTestName());
        test.setDescription(dto.getDescription());
        test.setExamTime(dto.getExamTime());  // Cập nhật ánh xạ examTime
        test.setExamDate(dto.getExamDate());
        test.setType(dto.getType());
        test.setMaxNumberOfExams(dto.getMaxNumberOfExams());
        test.setLevel(dto.getLevel());
        test.setPassingScore(dto.getPassingScore());
        test.setStatus(dto.getStatus());

        if (dto.getCourseId() != null) {
            Course course = new Course();
            course.setCourseId(dto.getCourseId());
            test.setCourse(course);
        }

        return test;
    }
}
