package com.example.block16ticket.ticket.repository;

import com.example.block16ticket.ticket.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
}
