//package com.example.final_project.entities;
//
//import jakarta.persistence.*;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import lombok.Getter;
//import lombok.Setter;
//import java.time.LocalDateTime;
//
//@EqualsAndHashCode(callSuper = false)
//@Setter
//@Getter
//@Entity
//@Table(name = "transactions", schema = "banking")
//@Data
//public class Transaction {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "from_account_number", nullable = false)
//    private Integer fromAccountNumber;
//
//    @Column(name = "to_account_number")
//    private Integer toAccountNumber;
//
//    @Column(nullable = false)
//    private Double amount;
//
//    @Column(nullable = false)
//    private String transactionType;
//
//    @Column(nullable = false)
//    private LocalDateTime timestamp;
//
//    public Transaction(Long id, Integer fromAccountNumber, Integer toAccountNumber, Double amount, String transactionType, LocalDateTime timestamp) {
//        this.id = id;
//        this.fromAccountNumber = fromAccountNumber;
//        this.toAccountNumber = toAccountNumber;
//        this.amount = amount;
//        this.transactionType = transactionType;
//        this.timestamp = timestamp;
//    }
//
//    public Transaction() {}
//}
//

package com.example.final_project.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions", schema = "banking")
@Data
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long transactionId;

    @Column(name = "from_account_number", nullable = false)
    private Integer fromAccountNumber;

    @Column(name = "to_account_number")
    private Integer toAccountNumber;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "transaction_type", nullable = false)
    private String transactionType;

    @Column(name = "transaction_timestamp", nullable = false)
    private LocalDateTime timestamp;

    public Transaction(Integer fromAccountNumber, Integer toAccountNumber, Double amount, String transactionType, LocalDateTime timestamp) {
        this.fromAccountNumber = fromAccountNumber;
        this.toAccountNumber = toAccountNumber;
        this.amount = amount;
        this.transactionType = transactionType;
        this.timestamp = timestamp;
    }
}
