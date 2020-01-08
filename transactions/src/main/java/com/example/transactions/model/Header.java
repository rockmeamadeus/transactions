package com.example.transactions.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Header {

    private String tipoRegistro;
    private String idPago;
    private String reservado;
    private Moneda moneda;
    private BigDecimal montoTotal;
    private BigDecimal totalDescuentos;
    private BigDecimal totalConDescuentos;
}
