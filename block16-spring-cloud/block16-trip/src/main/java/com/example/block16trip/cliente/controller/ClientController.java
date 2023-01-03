package com.example.block16trip.cliente.controller;

import com.example.block16trip.cliente.application.ClientService;

import com.example.block16trip.cliente.controller.dto.ClientInputDto;
import com.example.block16trip.cliente.controller.dto.ClientOutputDto;
import com.example.block16trip.cliente.domain.Client;
import com.example.block16trip.cliente.mapper.ClientMapper;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
public class ClientController {
    @Autowired
    ClientService clientService;

    @GetMapping("/find/{id}")
    public ClientOutputDto findClient (@PathVariable int id){
        Client client = clientService.searchById(id);
        return ClientMapper.Instance.clientToClientOutputDto(client);
    }

    @GetMapping("/all")
    public List<ClientOutputDto> allClient() {

        List<Client> clients = clientService.findAllClients();

        return clients
                .stream()
                .map(ClientMapper.Instance::clientToClientOutputDto)
                .toList();
    }

    @PostMapping("/addClient")
    public ClientOutputDto addClient (@RequestBody ClientInputDto clientInputDto){
        Client client = ClientMapper.Instance.clientInputDtoToClient(clientInputDto);
        clientService.addClient(client);
        return ClientMapper.Instance.clientToClientOutputDto(client);
    }

    @DeleteMapping("/deleted/{id}")
    public ResponseEntity<String> deletedById(@PathVariable int id){
        clientService.deletedClient(id);
        return ResponseEntity.ok().body("Client with "+id+" has been successfully removed ");
    }

    @PutMapping("/update/{id}")
    public ClientOutputDto updateClient (@PathVariable int id, @RequestBody ClientInputDto clientInputDto){
        Client client = ClientMapper.Instance.clientInputDtoToClient(clientInputDto);
        return ClientMapper.Instance.clientToClientOutputDto(clientService.updateClient(id, client));

    }

}
