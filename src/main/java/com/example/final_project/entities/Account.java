package com.example.final_project.entities;

import com.example.final_project.repository.AccountRepository;
import jakarta.persistence.*;

import lombok.*;

//Commented out @Getter @Setter because @Data should include both.
//@Getter
//@Setter

@Entity
@Table(name = "accounts", schema = "banking")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "account_number", nullable = false, unique = true)
    private Integer accountNumber;

    @Column(name = "sort_code", nullable = false)
    private Integer sortCode;

    @Column(name = "account_name", nullable = false)
    private String accountName;

    @Column(name = "account_type", nullable = false)
    private String accountType;

    @Column(name = "balance", nullable = false)
    private Double balance;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    public Account(Integer accountNumber, Integer sortCode, String accountName, String accountType, Double balance, Customer customer) {
        this.accountNumber = accountNumber;
        this.sortCode = sortCode;
        this.accountName = accountName;
        this.accountType = accountType;
        this.balance = balance;
        this.customer = customer;
    }

    public Account(int accountNumber, int sortCode, String accountName) {
        this.accountNumber = accountNumber;
        this.sortCode = sortCode;
        this.accountName = accountName;
        this.accountType = null;
        this.balance = null;
        this.customer = customer;
    }
}
