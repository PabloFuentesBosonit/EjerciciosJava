package com.example.facturaJPA.cliente.application;

import com.example.facturaJPA.cliente.controller.dto.ClienteInputDto;
import com.example.facturaJPA.cliente.controller.dto.ClienteMapper;
import com.example.facturaJPA.cliente.controller.dto.ClienteOutputDto;
import com.example.facturaJPA.cliente.domain.Cliente;
import com.example.facturaJPA.cliente.repository.ClienteRepository;
import com.example.facturaJPA.factura.controller.dto.FacturaMapper;
import com.example.facturaJPA.factura.controller.dto.FacturaOutputDto;
import com.example.facturaJPA.factura.domain.Factura;
import com.example.facturaJPA.linea.controller.dto.LineaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public ClienteOutputDto addCliente(ClienteInputDto clienteInputDto) {
        Cliente cliente = ClienteMapper.Instance.clienteInputToCliente(clienteInputDto);
        Cliente clienteDb = clienteRepository.save(cliente);
        return ClienteMapper.Instance.clienteToClienteOutputDto(clienteDb);
    }

    @Override
    public ClienteOutputDto getClienteById(int id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow();
        List<FacturaOutputDto> facturas = cliente.getFacturas()
                .stream()
                .map(FacturaMapper.Instance::facturaToFacturaOutputDto).toList();

        ClienteOutputDto response = ClienteMapper.Instance.clienteToClienteOutputDto(cliente);
        response.setFacturas(facturas);
        return response;
    }

    @Override
    public void deleteClienteById(int id) {
        clienteRepository.findById(id).orElseThrow();
        clienteRepository.deleteById(id);

    }

    @Override
    public List<ClienteOutputDto> getAllClientes(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return clienteRepository.findAll()
                .stream()
                .map(cliente -> ClienteMapper.Instance.clienteToClienteOutputDto(cliente)).toList();
    }

    @Override
    public ClienteOutputDto updateCliente(ClienteInputDto clienteInputDto, int id) throws Exception {
        Cliente clienteDb = clienteRepository.findById(id).orElseThrow();
        Cliente cliente = ClienteMapper.Instance.clienteInputToCliente(clienteInputDto);
        Boolean isEqual = Objects.equals(clienteDb, cliente);
        if(isEqual){
            throw new Exception();
        }
        clienteRepository.save(cliente);
        return ClienteMapper.Instance.clienteToClienteOutputDto(cliente);
    }
}
