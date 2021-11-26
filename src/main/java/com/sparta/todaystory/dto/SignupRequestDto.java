package com.sparta.todaystory.dto;

import lombok.*;

import javax.validation.constraints.*;

@Setter
@Getter
@ToString
@RequiredArgsConstructor
public class SignupRequestDto {

    private String username;

    private String password;

    private String password2;

    private String email;

    public SignupRequestDto(String username, String password, String password2,String email) {
        this.username = username;
        this.password = password;
        this.password2 = password2;
        this.email = email;
    }
}