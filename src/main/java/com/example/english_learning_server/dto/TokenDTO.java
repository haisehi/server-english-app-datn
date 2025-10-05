package com.example.english_learning_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenDTO {
    private Integer id;
    private String token;
    private String tokenType;
    private boolean expired;
    private boolean revoked;
}