package com.sparta.todaystory.service;


import com.sparta.todaystory.dto.SignupRequestDto;
import com.sparta.todaystory.model.User;
import com.sparta.todaystory.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;


    public boolean registerUser(SignupRequestDto requestDto) {
// 회원 ID 중복 확인
        String username = requestDto.getUsername();
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            return false;
        }

// 패스워드 암호화
        String password = passwordEncoder.encode(requestDto.getPassword());
        String email = requestDto.getEmail();

// 사용자 ROLE 확인

        User user = new User(username, password, email);
        userRepository.save(user);
        return true;
    }
}
