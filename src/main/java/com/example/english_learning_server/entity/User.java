package com.example.english_learning_server.entity;

import com.example.english_learning_server.entity.Token;
import com.example.english_learning_server.user.Role;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "phone", length = 20, nullable = false)
    private String phone;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "status", nullable = false)
    private int status = 1;

    @CreationTimestamp
    @Column(name = "create_date", updatable = false)
    private LocalDateTime createDate;

    @UpdateTimestamp
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @LastModifiedBy
    @Column(name = "created_by", length = 50, updatable = false)
    private String createdBy = "User";

    @Column(name = "updated_by", length = 50)
    private String updatedBy = "User";

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Token> tokens;



    // Thêm quan hệ với bảng UserCourse
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserCourse> userCourses = new ArrayList<>();

    // Thêm quan hệ với bảng UserLessons
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserLesson> userLessons = new ArrayList<>();

    // Thêm quan hệ với VocabularyUser
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<VocabularyUser> vocabularyUsers;

    // Thêm quan hệ với UserTest
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserTest> userTests = new ArrayList<>();

    // Thêm quan hệ với UserAnswer
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserAnswer> userAnswers = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
