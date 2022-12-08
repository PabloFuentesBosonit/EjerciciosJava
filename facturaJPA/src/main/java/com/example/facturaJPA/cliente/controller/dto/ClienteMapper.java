package com.example.facturaJPA.cliente.controller.dto;


import com.example.facturaJPA.cliente.domain.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClienteMapper {

    ClienteMapper Instance = Mappers.getMapper(ClienteMapper.class);

    ClienteOutputDto clienteToClienteOutputDto(Cliente cliente);

    Cliente clienteInputToCliente(ClienteInputDto clienteInputDto9);


}
