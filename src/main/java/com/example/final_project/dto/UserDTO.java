package com.example.final_project.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDTO {
    private Long userId;
    private String username;
    private List<AccountDTO> accounts;

    public UserDTO() {}

    public UserDTO(Long userId, String username, List<AccountDTO> accounts) {
        this.userId = userId;
        this.username = username;
        this.accounts = accounts;
    }
}
