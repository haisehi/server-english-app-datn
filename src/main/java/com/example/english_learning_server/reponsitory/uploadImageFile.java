package com.example.english_learning_server.reponsitory;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface uploadImageFile {
    String uploadImage(MultipartFile file) throws IOException;

}
