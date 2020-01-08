package com.example.transactions.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TipoTransaccion {

    APROBADO("1"), RECHAZADO("2");

    public String tipo;

    public static TipoTransaccion valueOfTipoTransaccion(String tipo) {
        for (TipoTransaccion t : values()) {
            if (t.tipo.equals(tipo)) {
                return t;
            }
        }
        return null;
    }
}
