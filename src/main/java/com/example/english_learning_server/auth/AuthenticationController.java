package com.example.english_learning_server.auth;

import com.example.english_learning_server.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final AuthenticationService service;
    // http://localhost:8080/api/v1/auth/register
//    @PostMapping("/register")
//    public ResponseEntity<AuthenticationResponse> register(
//            @RequestParam("fullName") String fullName,
//            @RequestParam("email") String email,
//            @RequestParam("password") String password,
//            @RequestParam("phone") String phone,
//            @RequestParam(value = "avatar", required = false) MultipartFile avatar) {
//
//        RegisterRequest request = new RegisterRequest();
//        request.setFullName(fullName);
//        request.setEmail(email);
//        request.setPassword(password);
//        request.setPhone(phone);
//        request.setAvatar(avatar);
//
//        return ResponseEntity.ok(service.register(request));
//    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody User request) {
        // Đăng ký người dùng và trả về phản hồi
        AuthenticationResponse response = authenticationService.register(request);
        return ResponseEntity.ok(response);
    }


    // api login:
    // http://localhost:8080/api/v1/auth/authenticate
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(service.authenticate(request));
    }

    // api refresh token:
    // http://localhost:8080/api/v1/auth/refresh-token
    @PostMapping("/refresh-token")
    public void refreshToken(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }
}
