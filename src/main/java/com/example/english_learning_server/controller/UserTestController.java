package com.example.english_learning_server.controller;

import com.example.english_learning_server.dto.RankedTestDTO;
import com.example.english_learning_server.dto.RankedTestDTO2;
import com.example.english_learning_server.dto.UserTestDTO;
import com.example.english_learning_server.entity.User;
import com.example.english_learning_server.service.UserTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user-test")
public class UserTestController {

    @Autowired
    private UserTestService userTestService;

    @PostMapping("/save")
    public ResponseEntity<UserTestDTO> createOrUpdateUserTest(@RequestBody UserTestDTO userTestDTO) {
        UserTestDTO savedUserTest = userTestService.createOrUpdateUserTest(userTestDTO);
        return ResponseEntity.ok(savedUserTest);
    }

    @GetMapping("/ranked-tests")
    public ResponseEntity<List<RankedTestDTO>> getRankedTests(@RequestParam Integer courseId) {
        List<RankedTestDTO> rankedTests = userTestService.getRankedTestsForUser(courseId);
        return ResponseEntity.ok(rankedTests);
    }

    @GetMapping("/ranked-tests-example")
    public ResponseEntity<List<RankedTestDTO2>> getRankedTests2(@RequestParam Integer courseId) {
        List<RankedTestDTO2> rankedTests = userTestService.getRankedTestsForUser2(courseId);
        return ResponseEntity.ok(rankedTests);
    }
}
