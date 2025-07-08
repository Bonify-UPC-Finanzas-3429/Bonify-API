package com.enphase.platform.bonifyapirest.bond.dto;

import java.time.LocalDate;

public record InstallmentDTO(
        Long id,
        int periodNumber,
        Long bondId,
        double interest,
        double amortization,
        double totalPayment,
        double remainingBalance,
        LocalDate paymentDate
) {}
