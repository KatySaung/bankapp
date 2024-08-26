package com.example.final_project.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class CustomerDTO {
    private Long customerId;
    private String username;
    private List<AccountDTO> accounts;

    public CustomerDTO() {}

    public CustomerDTO(Long customerId, String username, List<AccountDTO> accounts) {
        this.customerId = customerId;
        this.username = username;
        this.accounts = accounts;
    }
}
