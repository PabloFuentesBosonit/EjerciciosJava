package com.example.block16trip.viaje.application;

import com.example.block16trip.viaje.domain.Trip;

import java.util.List;

public interface TripService {
    Trip addTrip(Trip trip);
    Trip searchById(int id);
    void deletedTrip(int id);
    List<Trip> findAllTrips();
    Trip updateTrip(int id, Trip trip);

    Trip addPassenger(int idTrip, int idPassenger);
    int countPassenger(int id);
    void updateStatus(int idTrip, boolean status);
}
