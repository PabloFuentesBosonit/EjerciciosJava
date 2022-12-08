package com.example.facturaJPA.factura.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacturaInputDto {
    int id;
    double importeFactura;
}
