package com.enphase.platform.bonifyapirest.bond.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "installments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Installment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int periodNumber;

    private Long bondId;

    private double interest;
    private double amortization;
    private double totalPayment;
    private double remainingBalance;

    private LocalDate paymentDate;
}
