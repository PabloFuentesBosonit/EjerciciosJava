package com.example.facturaJPA.cliente.controller.dto;

import com.example.facturaJPA.factura.controller.dto.FacturaOutputDto;
import com.example.facturaJPA.factura.domain.Factura;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteOutputDto {
    int id;
    String nombre;
}
