package com.example.block16ticket.ticket.domain;

import lombok.Data;

import java.util.List;

@Data
public class Trip {
    int id;
    String origin;
    String destination;
    String departureDate;
    String arrivalDate;
    List<Client> passengers;
    boolean status;
}
