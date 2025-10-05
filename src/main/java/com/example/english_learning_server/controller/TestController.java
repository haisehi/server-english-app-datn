package com.example.english_learning_server.controller;

import com.example.english_learning_server.dto.TestDTO;
import com.example.english_learning_server.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tests")
public class TestController {

    @Autowired
    private TestService testService;

    @PostMapping
    public ResponseEntity<TestDTO> addTest(@RequestBody TestDTO testDTO) {
        TestDTO createdTest = testService.addTest(testDTO);
        return ResponseEntity.ok(createdTest);
    }

    @GetMapping
    public ResponseEntity<List<TestDTO>> getAllTests() {
        List<TestDTO> tests = testService.getAllTests();
        return ResponseEntity.ok(tests);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestDTO> getTestById(@PathVariable Integer id) {
        TestDTO test = testService.getTestById(id);
        if (test != null) {
            return ResponseEntity.ok(test);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<TestDTO>> getTestsByCourseId(@PathVariable Integer courseId) {
        List<TestDTO> tests = testService.getTestsByCourseId(courseId);
        return ResponseEntity.ok(tests);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TestDTO> updateTest(@PathVariable Integer id, @RequestBody TestDTO testDTO) {
        TestDTO updatedTest = testService.updateTest(id, testDTO);
        if (updatedTest != null) {
            return ResponseEntity.ok(updatedTest);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTest(@PathVariable Integer id) {
        boolean deleted = testService.deleteTest(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}