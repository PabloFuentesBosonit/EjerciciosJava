package com.example.facturaJPA.linea.application;

import com.example.facturaJPA.linea.controller.dto.LineaInputDto;
import com.example.facturaJPA.linea.controller.dto.LineaOutputDto;

public interface LineaService {

    LineaOutputDto addLinea(LineaInputDto lineaInputDto);
    LineaOutputDto getLineaById(int id);
    void deleteLineaById( int id);
    Iterable<LineaOutputDto> getAllLineas(int pageNumber, int pageSize);
    LineaOutputDto updateLinea(LineaInputDto lineaInputDto, int id) throws Exception;

}
