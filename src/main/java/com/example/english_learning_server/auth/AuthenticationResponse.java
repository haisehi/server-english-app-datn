package com.example.english_learning_server.auth;

import com.example.english_learning_server.user.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("role")
    private Role role; // Thêm thông tin về vai trò
    @JsonProperty("id_user")
    private Integer id; // Thêm thông tin về id user

    @JsonProperty("full_name_user")
    private String fullName; // Thêm thông tin về tên user
}
