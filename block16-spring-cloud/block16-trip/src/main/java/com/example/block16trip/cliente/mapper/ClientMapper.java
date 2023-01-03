package com.example.block16trip.cliente.mapper;

import com.example.block16trip.cliente.controller.dto.ClientInputDto;
import com.example.block16trip.cliente.controller.dto.ClientOutputDto;
import com.example.block16trip.cliente.domain.Client;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface ClientMapper {
    ClientMapper Instance = Mappers.getMapper(ClientMapper.class);

    Client clientInputDtoToClient(ClientInputDto clientInputDto);

    ClientOutputDto clientToClientOutputDto(Client client);

}
