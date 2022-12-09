package com.example.facturaJPA.factura.application;

import com.example.facturaJPA.factura.controller.dto.FacturaInputDto;
import com.example.facturaJPA.factura.controller.dto.FacturaOutputDto;
import com.example.facturaJPA.linea.controller.dto.LineaInputDto;

public interface FacturaService {

    FacturaOutputDto addFactura(FacturaInputDto facturaInputDto);
    FacturaOutputDto addLineas(LineaInputDto lineaInputDto, int idFactura);
    FacturaOutputDto getFacturaById(int id);
    void deleteFacturaById(int id);
    Iterable<FacturaOutputDto> getAllFacturas(int pageNumber, int pageSize);
    FacturaOutputDto updateFactura(FacturaInputDto facturaInputDto, int id) throws Exception;

}
