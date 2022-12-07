package com.example.facturaJPA.linea.controller.dto;

import com.example.facturaJPA.linea.domain.Linea;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LineaMapper {

    LineaMapper Instance = Mappers.getMapper(LineaMapper.class);

    LineaOutputDto lineaToLineaOutputDto(Linea linea);

    Linea lineaInputDtoToLinea(LineaInputDto lineaInputDto);
}
