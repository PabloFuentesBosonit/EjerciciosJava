package com.example.facturaJPA.factura.controller.dto;

import com.example.facturaJPA.cliente.Cliente;
import com.example.facturaJPA.linea.domain.Linea;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacturaOutputDto {
    int id;
    double importeFactura;
    Cliente cliente;
    List<Linea> lineas;
}
