package com.example.facturaJPA.factura.controller.dto;

import com.example.facturaJPA.factura.domain.Cliente;
import com.example.facturaJPA.factura.domain.Linea;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacturaOutput {
    int id;
    double importeFactura;
    Cliente cliente;
    List<Linea> lineas;
}
