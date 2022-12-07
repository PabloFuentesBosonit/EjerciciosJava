package com.example.facturaJPA.factura.controller.dto;

import com.example.facturaJPA.factura.domain.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacturaInput {
    int id;
    double importeFactura;
}
