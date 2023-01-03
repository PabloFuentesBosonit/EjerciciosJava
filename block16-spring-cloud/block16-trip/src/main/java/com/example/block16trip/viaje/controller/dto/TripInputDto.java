package com.example.block16trip.viaje.controller.dto;

import com.example.block16trip.cliente.domain.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripInputDto {
    String origin;
    String destination;
    String departureDate;
    String arrivalDate;
    List<Client> passengers;
    boolean status;
}
