package com.example.block16trip.viaje.domain;

import com.example.block16trip.cliente.domain.Client;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trip {
    @Id
    @GeneratedValue
    int id;
    String origin;
    String destination;
    String departureDate;
    String arrivalDate;

    @OneToMany
    List<Client> passengers;
    boolean status;

    public Trip(String origin, String destination, String departureDate, String arrivalDate, boolean status) {
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.status = status;
    }

}
