package com.example.english_learning_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Integer id;
    private String fullName;
    private String email;
    private String phone;
    private String avatar;
    private int status;
    private LocalDateTime createDate;
    private List<TokenDTO> tokens; // Danh sách token
}