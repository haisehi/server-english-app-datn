package com.example.english_learning_server.service;

import com.example.english_learning_server.converter.TestMapper;
import com.example.english_learning_server.dto.TestDTO;
import com.example.english_learning_server.entity.Test;
import com.example.english_learning_server.reponsitory.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TestService {

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private TestMapper testMapper;

    public TestDTO addTest(TestDTO testDTO) {
        // Nếu examDate chưa được truyền, set nó bằng ngày hiện tại
        if (testDTO.getExamDate() == null) {
            testDTO.setExamDate(LocalDate.now());  // Set ngày hiện tại
        }

        Test test = testMapper.toEntity(testDTO);
        test = testRepository.save(test);
        return testMapper.toDTO(test);
    }

    public List<TestDTO> getAllTests() {
        List<Test> tests = testRepository.findAll();
        return tests.stream().map(testMapper::toDTO).collect(Collectors.toList());
    }

    public TestDTO getTestById(Integer id) {
        Optional<Test> test = testRepository.findById(id);
        return test.map(testMapper::toDTO).orElse(null);
    }


    // Phương thức mới để lấy các bài kiểm tra theo ID khóa học
    public List<TestDTO> getTestsByCourseId(Integer courseId) {
        List<Test> tests = testRepository.findByCourse_CourseId(courseId);
        return tests.stream().map(testMapper::toDTO).collect(Collectors.toList());
    }

    public TestDTO updateTest(Integer id, TestDTO testDTO) {
        Optional<Test> optionalTest = testRepository.findById(id);
        if (optionalTest.isPresent()) {
            Test test = optionalTest.get();
            test.setTestName(testDTO.getTestName());
            test.setDescription(testDTO.getDescription());
            test.setExamTime(testDTO.getExamTime());
            test.setExamDate(testDTO.getExamDate());
            test.setType(testDTO.getType());
            test.setMaxNumberOfExams(testDTO.getMaxNumberOfExams());
            test.setLevel(testDTO.getLevel());
            test.setPassingScore(testDTO.getPassingScore());
            test.setStatus(testDTO.getStatus());
            test = testRepository.save(test);
            return testMapper.toDTO(test);
        }
        return null;
    }

    public boolean deleteTest(Integer id) {
        if (testRepository.existsById(id)) {
            testRepository.deleteById(id);
            return true;
        }
        return false;
    }
}