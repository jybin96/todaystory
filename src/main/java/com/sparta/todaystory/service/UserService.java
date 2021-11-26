package com.sparta.todaystory.service;


import com.sparta.todaystory.dto.SignupRequestDto;
import com.sparta.todaystory.model.User;
import com.sparta.todaystory.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;

import javax.validation.Valid;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

//    public void changedRepository(UserRepository userRepository){
//        this.userRepository = userRepository;
//    }

    //회원 가입 서비스
    public String registerUser(SignupRequestDto requestDto) {

        String velidString = verifyUser(requestDto);

        if(!velidString.equals("validSuccess"))
            return velidString;
        // 회원 ID 중복 확인
        String username = requestDto.getUsername();
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            return "idDuplicate";
        }
        //형식 확인
// 패스워드 암호화
        String password = passwordEncoder.encode(requestDto.getPassword());
        String email = requestDto.getEmail();
        User user = new User(username, password, email);
        userRepository.save(user);
        return "success";
    }

    public String verifyUser(SignupRequestDto signupRequestDto){
        String pattern = "^[A-za-z0-9]{3,}$";
        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();
        String password2 = signupRequestDto.getPassword2();
        //해당되는 아이디가 매치가 안된다면.
        if(!Pattern.matches(pattern,username))
            return "idValid";
        else if (username.equals(password)||password.length()<4||!password.equals(password2)||password.trim().equals(""))
            return "pwValid";

        return "validSuccess";
    }
}
