package com.example.block13criterialbuilder.controller.dto;

import com.example.block13criterialbuilder.domain.Person;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {

    PersonMapper Instance = Mappers.getMapper(PersonMapper.class);

    PersonOutputDto personToPersonOutputDTO(Person person);

    Person personInputDTOToPerson(PersonInputDto personInputDTO);
}
