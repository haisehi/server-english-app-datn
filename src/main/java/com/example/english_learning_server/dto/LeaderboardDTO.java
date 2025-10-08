package com.example.english_learning_server.dto;

import lombok.Data;

@Data
public class LeaderboardDTO {
    private Integer rank;        // vị trí xếp hạng
    private Integer userId;
    private String fullName;
    private String email;
    private String avatar;
    private Double totalScore;
}