package com.enphase.platform.bonifyapirest.bond.dto;

import com.enphase.platform.bonifyapirest.bond.model.valueobject.*;

import java.time.LocalDate;

public record BondRequestDTO(
        String name,
        double nominalValue,
        LocalDate issueDate,
        int termInYears,
        double annualInterestRate,
        RateType rateType,
        PaymentFrequency paymentFrequency,
        GracePeriodType gracePeriod,
        Long userId
) {}
