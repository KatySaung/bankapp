package com.example.final_project.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserDto {
    private String name;

    public RegisterUserDto(String name) {
        this.name = name;
    }

    public RegisterUserDto() {}
}
