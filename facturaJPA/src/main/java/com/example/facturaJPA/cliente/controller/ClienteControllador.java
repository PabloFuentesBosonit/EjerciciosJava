package com.example.facturaJPA.cliente.controller;

import com.example.facturaJPA.cliente.application.ClienteService;
import com.example.facturaJPA.cliente.controller.dto.ClienteInputDto;
import com.example.facturaJPA.cliente.controller.dto.ClienteOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
public class ClienteControllador {

    @Autowired
    ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteOutputDto> addCliente(@RequestBody ClienteInputDto clienteInputDto) {
        return ResponseEntity.ok().body(clienteService.addCliente(clienteInputDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteOutputDto> getClienteById(@PathVariable int id) {
        try {
            return ResponseEntity.ok().body(clienteService.getClienteById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <String> deleteClienteById(@PathVariable int id) {
        try {
            clienteService.deleteClienteById(id);
            return ResponseEntity.ok().body("cliente with id"+id+"was deleted");
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public Iterable<ClienteOutputDto> getAllClientes(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize){
        return clienteService.getAllClientes(pageNumber, pageSize);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteOutputDto> updateCliente(@RequestBody ClienteInputDto clienteInputDto, @PathVariable int id){
        try{
            clienteService.getClienteById(id);
            return ResponseEntity.ok().body(clienteService.addCliente(clienteInputDto));
        } catch(Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
