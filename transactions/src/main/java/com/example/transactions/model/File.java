package com.example.transactions.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
public class File implements Serializable {

    private Header header;
    private List<Transaccion> transaccions;
    private List<Descuento> descuentos;
    private Footer footer;

    public File(String header, String transactions, String descuentos, String footer) {

        this.header = Header.builder()
                .tipoRegistro(header.substring(0, 1))
                .idPago(header.substring(1, 33))
                //.reservado(header.substring(33, 36))
                .moneda(Moneda.valueOfMoneda(header.substring(36, 39)))
                .montoTotal(new BigDecimal(header.substring(39, 52)))
                .totalDescuentos(new BigDecimal(header.substring(52, 65)))
                .totalConDescuentos(new BigDecimal(header.substring(65, 78)))
                .build();

        this.transaccions = Stream.of(transactions)
                .map(s -> s.split("\\n"))
                .map(strings -> Arrays.asList(strings))
                .flatMap(strings -> strings.stream())
                .map(s -> Transaccion.builder()
                        .tipoRegistro(s.substring(0, 1))
                        .idTransaccion(s.substring(1, 33))
                        .monto(new BigDecimal(s.substring(33, 46)))
                        //.reservado(s.substring(46, 51))
                        .tipo(TipoTransaccion.valueOfTipoTransaccion(s.substring(51)))
                        .build()).
                        collect(Collectors.toList());

        this.descuentos = Stream.of(descuentos)
                .map(s -> s.split("\\n"))
                .map(strings -> Arrays.asList(strings))
                .flatMap(strings -> strings.stream())
                .map(s -> Descuento.builder()
                        .tipoRegistro(s.substring(0, 1))
                        .idDescuento(s.substring(1, 33))
                        .monto(new BigDecimal(s.substring(33, 46)))
                        //.reservado(s.substring(46, 49))
                        .tipo(TipoDescuento.valueOfTipoDescuento(Integer.valueOf(s.substring(49, 50))))
                        .build())
                .collect(Collectors.toList());

        this.footer = Footer.builder()
                .tipoRegistro(footer.substring(0, 1))
                //.reservado(footer.substring(1, 16))
//                .fechaPago(LocalDate.parse(footer.substring(16, 24), DateTimeFormatter.ofPattern("yyyyMMdd")))

                .fechaPago(footer.substring(16, 24))
                .idCliente(footer.substring(24, 56))
                .build();
    }

}
