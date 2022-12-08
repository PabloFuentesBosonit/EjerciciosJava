package com.example.facturaJPA.cliente.application;

import com.example.facturaJPA.cliente.controller.dto.ClienteInputDto;
import com.example.facturaJPA.cliente.controller.dto.ClienteOutputDto;

public interface ClienteService {

    ClienteOutputDto addCliente(ClienteInputDto clienteInputDto);
    ClienteOutputDto getClienteById(int id);
    void deleteClienteById(int id);
    Iterable<ClienteOutputDto> getAllClientes(int pageNumber, int pageSize);
    ClienteOutputDto updateCliente(ClienteInputDto clienteInputDto, int id) throws Exception;
}
