package com.example.final_project.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDTO {
    // setter and getter
    private String username;
    private String password;
    public UserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
    // default
    public UserDTO(){}

}
