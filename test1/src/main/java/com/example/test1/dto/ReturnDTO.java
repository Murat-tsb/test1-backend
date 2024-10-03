package com.example.test1.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReturnDTO {
    private String companyName;
    private Long commission;
    private LocalDate returnDate;
    private Long paymentAmount;
    private Long totalPayment;
    public ReturnDTO(String companyName, Long commission, LocalDate returnDate, Long paymentAmount, Long totalPayment) {
        this.companyName = companyName;
        this.commission = commission;
        this.returnDate = returnDate;
        this.paymentAmount = paymentAmount;
        this.totalPayment = totalPayment;
    }

}