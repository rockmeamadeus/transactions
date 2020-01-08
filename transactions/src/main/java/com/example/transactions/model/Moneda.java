package com.example.transactions.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Moneda {
    ARS("000"), USD("001");
    private String moneda;

    public static Moneda valueOfMoneda(String moneda) {
        for (Moneda m : values()) {
            if (m.moneda.equals(moneda)) {
                return m;
            }
        }
        return null;
    }
}
