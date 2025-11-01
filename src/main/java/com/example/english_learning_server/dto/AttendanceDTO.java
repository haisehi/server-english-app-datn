package com.example.english_learning_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AttendanceDTO {
    private Long id;
    private String attendance_date;
    private String status;
    private Integer user_id;
}
