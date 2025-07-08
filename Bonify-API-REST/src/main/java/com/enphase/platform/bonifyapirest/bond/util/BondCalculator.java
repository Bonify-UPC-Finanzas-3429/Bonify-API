package com.enphase.platform.bonifyapirest.bond.util;

import com.enphase.platform.bonifyapirest.bond.model.valueobject.PaymentFrequency;

import java.util.Map;

public class BondCalculator {

    public static final Map<PaymentFrequency, Integer> FREQUENCY_MAP = Map.of(
            PaymentFrequency.MENSUAL, 12,
            PaymentFrequency.BIMESTRAL, 6,
            PaymentFrequency.TRIMESTRAL, 4,
            PaymentFrequency.CUATRIMESTRAL, 3,
            PaymentFrequency.SEMESTRAL, 2,
            PaymentFrequency.ANUAL, 1,
            PaymentFrequency.QUINCENAL, 24
    );

    public static double calculateRatePerPeriod(double annualRate, int paymentsPerYear, boolean isEffective) {
        return isEffective
                ? Math.pow(1 + annualRate / 100, 1.0 / paymentsPerYear) - 1
                : (annualRate / 100) / paymentsPerYear;
    }

    public static double calculateInstallment(double nominalValue, double ratePerPeriod, int totalInstallments) {
        return nominalValue * ratePerPeriod / (1 - Math.pow(1 + ratePerPeriod, -totalInstallments));
    }

    public static double calculateTEA(double ratePerPeriod, int paymentsPerYear) {
        return Math.pow(1 + ratePerPeriod, paymentsPerYear) - 1;
    }
}
