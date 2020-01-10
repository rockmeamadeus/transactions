package com.example.transactions.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Descuento {

    private String tipoRegistro;
    private String idDescuento;
    private BigDecimal monto;
    //private String reservado;
    private TipoDescuento tipo;

}
