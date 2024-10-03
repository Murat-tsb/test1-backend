package com.example.test1.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "returns")
public class Returns {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = true, updatable = false, name = "return_date")
    private LocalDate returnDate;
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "payment_amount")
    private Long paymentAmount;
    @Column(name = "commission")
    private Long commission;
    @Column(name = "total_payment")
    private Long totalPayment;
}
