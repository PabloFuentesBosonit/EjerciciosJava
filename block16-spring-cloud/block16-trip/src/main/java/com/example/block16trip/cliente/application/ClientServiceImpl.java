package com.example.block16trip.cliente.application;

import com.example.block16trip.cliente.domain.Client;
import com.example.block16trip.cliente.repository.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class ClientServiceImpl implements ClientService{

    @Autowired
    ClientRepository clientRepository;

    @Override
    public Client addClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client searchById(int id) {
       log.info("Searching user with given id {}", id);
       return clientRepository.findById(id)
               .orElseThrow(() -> new EntityNotFoundException("Client with this id "+ id +" not found"));
    }


    @Override
    public void deletedClient(int id) {
        log.info("Deleting user with id {}", id);
        this.searchById(id);
        clientRepository.deleteById(id);
    }

    @Override
    public List<Client> findAllClients() {
        log.info("Searching all users");
        return clientRepository.findAll();
    }

    @Override
    public Client updateClient(int id, Client client) {
        log.info("Saving user with data {}", client);
        Client clientDb = this.searchById(id);
        if(client == clientDb){
            log.error("Contienen la misma información");
        }
        client.setId(id);
        return clientRepository.save(client);

    }
}
