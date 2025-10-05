package com.example.english_learning_server.service;

import com.example.english_learning_server.converter.UserMapper;
import com.example.english_learning_server.dto.UserDTO;
import com.example.english_learning_server.entity.User;
import com.example.english_learning_server.reponsitory.UserReponsitory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserReponsitory userRepository;
    private final UserMapper userMapper;

    // Lấy tất cả người dùng và chuyển đổi sang DTO
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Lấy người dùng theo ID và chuyển đổi sang DTO
    public Optional<UserDTO> getUserById(Integer id) {
        return userRepository.findById(id)
                .map(userMapper::toDTO);
    }

    // Lấy thông tin người dùng hiện tại và chuyển đổi sang DTO
    public UserDTO getCurrentUser() {
        String currentEmail = getCurrentUserEmail();
        User user = userRepository.findByEmail(currentEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toDTO(user);
    }

    private String getCurrentUserEmail() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof org.springframework.security.core.userdetails.UserDetails) {
            return ((org.springframework.security.core.userdetails.UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }
}