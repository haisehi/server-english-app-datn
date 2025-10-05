package com.example.english_learning_server.auth;

import com.example.english_learning_server.user.Role;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String fullName;
    private String email;
    private String password;

    private String phone;

    private MultipartFile avatar;

    private int status = 1;

    private LocalDateTime createDate;

    private LocalDateTime updatedDate;

    private String createdBy;

    private String updatedBy;

    private Role role;
}
