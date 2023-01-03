package com.example.block16ticket.ticket.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Ticket {

    @Id
    @GeneratedValue
    int id;
    int idPassenger;
    String namePassenger;
    String surnamePassenger;
    String emailPassenger;
    String tripOrigin;
    String tripDestination;
    String arrivalDate;
    String departureDate;

}
