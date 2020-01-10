package com.example.transactions.model;

import lombok.Builder;
import lombok.Data;

import java.beans.BeanInfo;
import java.math.BigDecimal;

@Data
@Builder
public class Transaccion {

    private String tipoRegistro;
    private String idTransaccion;
    private BigDecimal monto;
    //private String reservado;
    private TipoTransaccion tipo;
}
