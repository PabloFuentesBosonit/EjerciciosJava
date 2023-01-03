package com.example.block16ticket.ticket.application;

import com.example.block16ticket.ticket.domain.Ticket;

public interface TicketService {
    Ticket generateTicket(int idPassenger, int idTrip);
}
