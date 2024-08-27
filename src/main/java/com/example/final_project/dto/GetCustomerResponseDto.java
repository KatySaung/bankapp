package com.example.final_project.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GetCustomerResponseDto {
        private Integer id;
        private String fullName;
        private Integer[] accounts;

        public GetCustomerResponseDto() {}
        public GetCustomerResponseDto(Integer id, String fullName, Integer[] accounts) {
            this.id = id;
            this.fullName = fullName;
            this.accounts = accounts;
        }

}
