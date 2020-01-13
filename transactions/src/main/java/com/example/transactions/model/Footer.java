package com.example.transactions.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
public class Footer {

    private String tipoRegistro;
    //private String reservado;
    private String fechaPago;
    private String idCliente;

}
