package com.example.transactions.model;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
public enum TipoDescuento {

    IVA(0), RETENCIONES(1), COMISIONES(2), COSTOS_EXTRA(3), INGRESOS_BRUTOS(4);

    public int tipo;

    public static TipoDescuento valueOfTipoDescuento(int tipo) {
        for (TipoDescuento t : values()) {
            if (t.tipo == tipo) {
                return t;
            }
        }
        return null;
    }
}
