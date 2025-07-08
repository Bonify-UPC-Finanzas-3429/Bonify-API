package com.enphase.platform.bonifyapirest.shared.util;

public class FinancialUtils {

    public static double round2(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    public static double round7(double value) {
        return Math.round(value * 1e7) / 1e7;
    }
}
