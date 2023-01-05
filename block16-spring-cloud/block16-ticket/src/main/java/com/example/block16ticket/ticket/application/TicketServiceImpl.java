package com.example.block16ticket.ticket.application;

import com.example.block16ticket.ticket.domain.Client;
import com.example.block16ticket.ticket.domain.Ticket;
import com.example.block16ticket.ticket.domain.Trip;
import com.example.block16ticket.ticket.repository.TicketRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;


@Service
@Slf4j
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Override
    public Ticket generateTicket(int idPassenger, int idTrip) {
        HashMap<String, Integer> ids = new HashMap<>();
        ids.put("idPassenger",idPassenger);
        ids.put("idTrip", idTrip);

        ResponseEntity<Client> client = new RestTemplate()
                .getForEntity("http://trip:8081/find/{idPassenger}", Client.class, ids);
        Client client1 = client.getBody();

        ResponseEntity<Trip> trip = new RestTemplate()
                .getForEntity("http://trip:8081/trip/{idTrip}", Trip.class, ids);
        Trip trip1 = trip.getBody();

        Ticket ticket = new Ticket();
        ticket.setIdPassenger(client1.getId());
        ticket.setNamePassenger(client1.getName());
        ticket.setSurnamePassenger(client1.getSurname());
        ticket.setEmailPassenger(client1.getEmail());
        ticket.setTripOrigin(trip1.getOrigin());
        ticket.setTripDestination(trip1.getDestination());
        ticket.setDepartureDate(trip1.getDepartureDate());
        ticket.setArrivalDate(trip1.getArrivalDate());

        return ticketRepository.save(ticket);

    }
}
