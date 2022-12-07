package com.example.facturaJPA.factura.application;

import com.example.facturaJPA.factura.controller.dto.FacturaInputDto;
import com.example.facturaJPA.factura.controller.dto.FacturaOutputDto;

public interface FacturaService {

    FacturaOutputDto addFactura(FacturaInputDto facturaInputDto);
    FacturaOutputDto getFacturaById(int id);
    void deleteFacturaById( int id);
    Iterable<FacturaOutputDto> getAllFacturas(int pageNumber, int pageSize);
    FacturaOutputDto updateFactura(FacturaInputDto facturaInputDto, int id) throws Exception;

}
