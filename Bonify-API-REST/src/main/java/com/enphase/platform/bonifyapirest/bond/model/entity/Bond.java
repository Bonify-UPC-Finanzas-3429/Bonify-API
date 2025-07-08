package com.enphase.platform.bonifyapirest.bond.model.entity;

import com.enphase.platform.bonifyapirest.bond.model.valueobject.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "bonds")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bond {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bondId;
    private String name;

    private double nominalValue;

    private LocalDate issueDate;
    private int termInYears;

    private double annualInterestRate;

    @Enumerated(EnumType.STRING)
    private RateType rateType;

    @Enumerated(EnumType.STRING)
    private PaymentFrequency paymentFrequency;

    @Enumerated(EnumType.STRING)
    private GracePeriodType gracePeriod;

    private int calculatedInstallment;
    private double tcea;
    private double tea;

    private Long userId;
}
