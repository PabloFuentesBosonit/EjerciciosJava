package com.example.block16trip.cliente.application;


import com.example.block16trip.cliente.domain.Client;

import java.util.List;

public interface ClientService {
        Client addClient(Client client);
        Client searchById(int id);
        void deletedClient(int id);
        List<Client> findAllClients();
        Client updateClient(int id, Client client);
}
