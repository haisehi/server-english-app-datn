package com.example.english_learning_server.controller;

import com.example.english_learning_server.dto.LessonDTO;
import com.example.english_learning_server.converter.LessonMapper;
import com.example.english_learning_server.dto.UserLessonDTO;
import com.example.english_learning_server.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/lessons")
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @Autowired
    private LessonMapper lessonMapper;

    // them bai hoc: http://localhost:8080/api/v1/lessons
    @PostMapping
    public ResponseEntity<LessonDTO> addLesson(@RequestBody LessonDTO lessonDTO) {
        LessonDTO savedLesson = lessonMapper.toDTO(
                lessonService.addLesson(lessonDTO)
        );
        return new ResponseEntity<>(savedLesson, HttpStatus.CREATED);
    }

    // hien thi tat ca khoa hoc: http://localhost:8080/api/v1/lessons
    @GetMapping
    public ResponseEntity<List<LessonDTO>> getAllLessons() {
        List<LessonDTO> lessons = lessonService.getAllLessons().stream()
                .map(lessonMapper::toDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(lessons, HttpStatus.OK);
    }

    // hien thi khoa hoc theo lesson id : http://localhost:8080/api/v1/lessons/1
    @GetMapping("/{lessonId}")
    public ResponseEntity<LessonDTO> getLessonById(@PathVariable Integer lessonId) {
        return lessonService.getLessonById(lessonId)
                .map(lesson -> new ResponseEntity<>(lessonMapper.toDTO(lesson), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // cap nhat : http://localhost:8080/api/v1/lessons/1
    @PutMapping("/{lessonId}")
    public ResponseEntity<LessonDTO> updateLesson(@PathVariable Integer lessonId, @RequestBody LessonDTO lessonDTO) {
        LessonDTO updatedLesson = lessonMapper.toDTO(
                lessonService.updateLesson(lessonId, lessonDTO)
        );
        return updatedLesson != null ? new ResponseEntity<>(updatedLesson, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    // xoa : http://localhost:8080/api/v1/lessons/8
    @DeleteMapping("/{lessonId}")
    public ResponseEntity<Void> deleteLesson(@PathVariable Integer lessonId) {
        boolean isDeleted = lessonService.deleteLesson(lessonId);
        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // hien thi theo course id : http://localhost:8080/api/v1/lessons/course/1
    @GetMapping("/course/{course-Id}")
    public ResponseEntity<List<LessonDTO>> getLessonsByCourseId(@PathVariable Integer courseId) {
        List<LessonDTO> lessons = lessonService.getLessonsByCourseId(courseId).stream()
                .map(lessonMapper::toDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(lessons, HttpStatus.OK);
    }

//    @GetMapping("/level/{level}")
//    public ResponseEntity<List<LessonDTO>> getLessonsByLevel(@PathVariable int level) {
//        List<LessonDTO> lessons = lessonService.getLessonsByLevel(level).stream()
//                .map(lessonMapper::toDTO)
//                .collect(Collectors.toList());
//        return new ResponseEntity<>(lessons, HttpStatus.OK);
//    }

    // lay du lieu theo access - token : http://localhost:8080/api/v1/lessons/user
    @GetMapping("/user")
    public ResponseEntity<List<LessonDTO>> getLessonsForCurrentUser(
            @RequestHeader("Authorization") String token,
            @RequestParam("courseId") Integer courseId) {
        // Gọi phương thức từ LessonService để lấy danh sách bài học cho người dùng hiện tại
        List<LessonDTO> lessons = lessonService.getLessonsByCourseForCurrentUser(courseId);

        return ResponseEntity.ok(lessons); // Trả về danh sách bài học với HTTP status 200
    }
}