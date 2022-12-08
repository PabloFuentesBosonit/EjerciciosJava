package com.example.facturaJPA.linea.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LineaInputDto {
    String producto;

    double cantidad;

    double importe;
}
