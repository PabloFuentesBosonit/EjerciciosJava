package com.example.block16ticket.ticket.controller;
import com.example.block16ticket.ticket.application.TicketService;
import com.example.block16ticket.ticket.domain.Ticket;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
public class TicketController {
    private TicketService ticketService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("generateTicket/{idPassenger}/{idTrip}")
    public Ticket generateTicket(@PathVariable("idPassenger") int passengerId, @PathVariable("idTrip") int idTrip) {
        return ticketService.generateTicket(passengerId, idTrip);

    }
}
