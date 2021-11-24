package com.sparta.todaystory.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.todaystory.dto.SignupRequestDto;
import com.sparta.todaystory.security.UserDetailsImpl;
import com.sparta.todaystory.service.KakaoUserService;
import com.sparta.todaystory.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final KakaoUserService kakaoUserService;


    // 회원 로그인 페이지
    @GetMapping("/user/login")
    public String login(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
        if(userDetails != null){ //로그인 하였는데 또 들어올경우
            model.addAttribute("islogin",true);
        }
        return "login";
    }

    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
        if(userDetails != null){ //로그인 하였는데 또 들어올경우
            model.addAttribute("islogin",true);
        }
        return "signup";
    }

    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    @ResponseBody
    public boolean registerUser(@RequestBody SignupRequestDto requestDto) {
        return userService.registerUser(requestDto);
    }

    @GetMapping("/user/kakao/callback")
    public String kakaoLogin(@RequestParam String code) throws JsonProcessingException {
// authorizedCode: 카카오 서버로부터 받은 인가 코드
        kakaoUserService.kakaoLogin(code);
        return "redirect:/";
    }
}