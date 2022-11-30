package com.example.block7crudvalidation.profesor.controller.dto;

import com.example.block7crudvalidation.profesor.domain.Profesor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProfesorMapper {
    ProfesorMapper Instance = Mappers.getMapper(ProfesorMapper.class);

    ProfesorOutputDto profesorToProfesorOutputDTO(Profesor profesor);

    Profesor profesorInputDTOToProfesor(ProfesorInputDto profesorInputDto);

}
