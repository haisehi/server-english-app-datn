package com.example.english_learning_server.converter;

import com.example.english_learning_server.dto.TokenDTO;
import com.example.english_learning_server.dto.UserDTO;
import com.example.english_learning_server.entity.Token;
import com.example.english_learning_server.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    // Chuyển từ User sang UserDTO
    public UserDTO toDTO(User user) {
        if (user == null) {
            return null;
        }
        return new UserDTO(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getPhone(),
                user.getAvatar(),
                user.getStatus(),
                user.getTokens() != null ? toTokenDTOList(user.getTokens()) : null // Chuyển danh sách Token
        );
    }

    // Chuyển danh sách Token sang TokenDTO
    private List<TokenDTO> toTokenDTOList(List<Token> tokens) {
        return tokens.stream()
                .map(this::toTokenDTO)
                .collect(Collectors.toList());
    }

    // Chuyển từ Token sang TokenDTO
    private TokenDTO toTokenDTO(Token token) {
        return new TokenDTO(
                token.getId(),
                token.getToken(),
                token.getTokenType().name(),
                token.isExpired(),
                token.isRevoked()
        );
    }

    // Chuyển từ UserDTO sang User (nếu cần)
    public User toEntity(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        User user = new User();
        user.setId(userDTO.getId());
        user.setFullName(userDTO.getFullName());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setAvatar(userDTO.getAvatar());
        user.setStatus(userDTO.getStatus());
        return user;
    }
}