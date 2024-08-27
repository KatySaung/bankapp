//package com.example.final_project.entities;
//
//import jakarta.persistence.*;
//import lombok.Data;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.util.concurrent.atomic.AtomicInteger;
//
//@Entity
//@Table(name = "accounts", schema = "banking")
//@Data
//public class Account {
////    private AtomicInteger accId = new AtomicInteger(1);
////
////    private AtomicInteger routId = new AtomicInteger(1);
////
////    public int generateUniqueAccId(){
////        return accId.getAndIncrement();
////    }
////
////    public int generateUniqueRoutId(){
////        return routId.getAndIncrement();
////    }
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(nullable = false)
//    private double balance;
//
//    @Column(nullable = false)
//    private String accountType;
//
//    @Column(nullable = false, unique = true)
//    private int accountNumber;
//
////    @Column(nullable = false, unique = true)
////    private int routNum;
//
//    @ManyToOne(optional = false)
//    @JoinColumn(name = "account_customer_id", nullable = false)
//    private Customer customer;
//
//    public Account(double balance, String accountType, Integer accountNumber, Customer customer) {
//        this.balance = balance;
//        this.accountType = accountType;
//        this.accountNumber = accountNumber;
//        //this.routNum = routNum;
//        this.customer = customer;
//    }
//
//
//    public Account() {}
//}



package com.example.final_project.entities;

import jakarta.persistence.*;
        import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "accounts", schema = "banking")
@Data
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "account_number", nullable = false, unique = true)
    private Integer accountNumber;

    @Column(name = "routing_number", nullable = false)
    private Integer routingNumber;

    @Column(name = "account_name", nullable = false)
    private String accountName;

    @Column(name = "balance", nullable = false)
    private Double balance;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    public Account(Integer accountNumber, Integer routingNumber, String accountName, Double balance, Customer customer) {
        this.accountNumber = accountNumber;
        this.routingNumber = routingNumber;
        this.accountName = accountName;
        this.balance = balance;
        this.customer = customer;
    }

}
