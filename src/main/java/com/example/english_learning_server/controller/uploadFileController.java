package com.example.english_learning_server.controller;

import com.example.english_learning_server.reponsitory.uploadImageFile;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping("/api/v1/image")
@RestController
@RequiredArgsConstructor
public class uploadFileController {
    private final uploadImageFile uploadImageFile;

    @PostMapping("/addImage")
    public String uploadImage(@RequestParam("file")MultipartFile file) throws IOException {
        String urlImage = uploadImageFile.uploadImage(file);
        return urlImage;
    }
}
