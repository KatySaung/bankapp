package com.example.final_project.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterUserDto {
    private String name;
    private String password;

    public RegisterUserDto(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public RegisterUserDto() {}
}
