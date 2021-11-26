package com.sparta.todaystory.service;

import com.sparta.todaystory.controller.UserController;
import com.sparta.todaystory.dto.SignupRequestDto;
import com.sparta.todaystory.model.User;
import com.sparta.todaystory.repository.UserRepository;
import com.sparta.todaystory.security.UserDetailsImpl;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Nested;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import javax.validation.Valid;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;


    @Test
    @DisplayName("정상적인 회원가입1")
    void insertUserNomal(){
        String username = "jybin96";
        String password = "asdasd123";
        String password2 = "asdasd123";
        String email = "jybin96@naver.com";
        SignupRequestDto signupRequestDto = new SignupRequestDto(username,password,password2,email);

        UserService userService = new UserService(passwordEncoder, userRepository);

        User user = new User(signupRequestDto);

        //when
        String result = userService.registerUser(signupRequestDto);
        String result2 = userService.registerUser(signupRequestDto);
        assertEquals("success",result);
        assertEquals("success",result2);
    }
    @Test
    @DisplayName("정상적인 회원가입2")
    void insertUserNomal2(){
        String username = "abc123";
        String password = "asda12";
        String password2 = "asda12";
        String email = "jybin96@naver.com";
        SignupRequestDto signupRequestDto = new SignupRequestDto(username,password,password2,email);

        UserService userService = new UserService(passwordEncoder, userRepository);

        User user = new User(signupRequestDto);
        //when
        String result = userService.registerUser(signupRequestDto);
        assertEquals("success",result);
    }
    @Test
    @DisplayName("비정상) 아이디 중복1")
    void idDuplicateUnNomal(){
        String username = "snsk3779";
        String password = "asdasd123";
        String password2 = "asdasd123";
        String email = "jybin96@naver.com";
        SignupRequestDto signupRequestDto = new SignupRequestDto(username,password,password2,email);

        UserService userService = new UserService(passwordEncoder, userRepository);

        User user = new User(signupRequestDto);

        when(userRepository.findByUsername(username))
                .thenReturn(Optional.of(user));
        //when
        String result = userService.registerUser(signupRequestDto);
        assertEquals("idDuplicate",result);
    }
    @Test
    @DisplayName("비정상) 아이디 중복2")
    void idDuplicateUnNomal2(){
        String username = "jybin96";
        String password = "asdasd123";
        String password2 = "asdasd123";
        String email = "jybin96@naver.com";
        SignupRequestDto signupRequestDto = new SignupRequestDto(username,password,password2,email);

        UserService userService = new UserService(passwordEncoder, userRepository);

        User user = new User(signupRequestDto);

        when(userRepository.findByUsername(username))
                .thenReturn(Optional.of(user));
        //when
        String result = userService.registerUser(signupRequestDto);
        assertEquals("idDuplicate",result);
    }
    @Test
    @DisplayName("비정상) 아이디 형식1")
    void idValidcheck(){
        String username = "ㅋㅋㅋㅋ!!!!";
        String password = "asdasd123";
        String password2 = "asdasd123";
        String email = "jybin96@naver.com";
        SignupRequestDto signupRequestDto = new SignupRequestDto(username,password,password2,email);

        UserService userService = new UserService(passwordEncoder, userRepository);

        User user = new User(signupRequestDto);

        //when
        String result = userService.registerUser(signupRequestDto);
        assertEquals("idValid",result);
    }
    @Test
    @DisplayName("비정상) 아이디 형식2")
    void idValidcheck2(){
        String username = "aa";
        String password = "asdasd123";
        String password2 = "asdasd123";
        String email = "jybin96@naver.com";
        SignupRequestDto signupRequestDto = new SignupRequestDto(username,password,password2,email);

        UserService userService = new UserService(passwordEncoder, userRepository);

        User user = new User(signupRequestDto);

        //when
        String result = userService.registerUser(signupRequestDto);
        assertEquals("idValid",result);
    }
    @Test
    @DisplayName("비정상) 비밀번호 형식1")
    void pwValidcheck1(){
        String username = "jybin96";
        String password = "aa";
        String password2 = "aa";
        String email = "jybin96@naver.com";
        SignupRequestDto signupRequestDto = new SignupRequestDto(username,password,password2,email);

        UserService userService = new UserService(passwordEncoder, userRepository);

        User user = new User(signupRequestDto);

        //when
        String result = userService.registerUser(signupRequestDto);
        assertEquals("pwValid",result);
    }
    @Test
    @DisplayName("비정상) 비밀번호 형식2")
    void pwValidcheck2(){
        String username = "jybin96";
        String password = "jybin96";
        String password2 = "jybin96";
        String email = "jybin96@naver.com";
        SignupRequestDto signupRequestDto = new SignupRequestDto(username,password,password2,email);

        UserService userService = new UserService(passwordEncoder, userRepository);

        User user = new User(signupRequestDto);

        //when
        String result = userService.registerUser(signupRequestDto);
        assertEquals("pwValid",result);
    }
    @Test
    @DisplayName("비정상) 비밀번호 확인1")
    void pwIdSamecheck1(){
        String username = "jybin96";
        String password = "123asd";
        String password2 = "asd123";
        String email = "jybin96@naver.com";
        SignupRequestDto signupRequestDto = new SignupRequestDto(username,password,password2,email);

        UserService userService = new UserService(passwordEncoder, userRepository);

        User user = new User(signupRequestDto);

        //when
        String result = userService.registerUser(signupRequestDto);
        assertEquals("pwValid",result);
    }
    @Test
    @DisplayName("비정상) 비밀번호 확인2")
    void pwIdSamecheck2(){
        String username = "snsk3779";
        String password = "snsk";
        String password2 = "snsk39";
        String email = "jybin96@naver.com";
        SignupRequestDto signupRequestDto = new SignupRequestDto(username,password,password2,email);

        UserService userService = new UserService(passwordEncoder, userRepository);

        User user = new User(signupRequestDto);

        //when
        String result = userService.registerUser(signupRequestDto);
        assertEquals("pwValid",result);
    }
}