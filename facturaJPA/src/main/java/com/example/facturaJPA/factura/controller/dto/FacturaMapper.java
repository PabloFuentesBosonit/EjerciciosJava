package com.example.facturaJPA.factura.controller.dto;

import com.example.facturaJPA.factura.domain.Factura;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FacturaMapper {

    FacturaMapper Instance = Mappers.getMapper(FacturaMapper.class);

    FacturaOutputDto facturaToFacturaOutputDto(Factura factura);

    Factura facturaInputDtoToFactura(FacturaInputDto facturaInputDto);
}
