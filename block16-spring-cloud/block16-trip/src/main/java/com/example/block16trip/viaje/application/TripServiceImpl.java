package com.example.block16trip.viaje.application;

import com.example.block16trip.cliente.application.ClientService;
import com.example.block16trip.cliente.domain.Client;
import com.example.block16trip.viaje.domain.Trip;
import com.example.block16trip.viaje.repository.TripRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TripServiceImpl implements TripService {

    @Autowired
    TripRepository tripRepository;

    @Autowired
    ClientService clientService;

    @Override
    public Trip addTrip(Trip trip) {
        return tripRepository.save(trip);
    }

    @Override
    public Trip searchById(int id) {
        log.info("Searching trip with given id {}", id);
        return tripRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Trip with this id " + id + " not found"));
    }

    @Override
    public void deletedTrip(int id) {
        log.info("Deleting trip with id {}", id);
        this.searchById(id);
        tripRepository.deleteById(id);
    }

    @Override
    public List<Trip> findAllTrips() {
        log.info("Searching all trip");
        return tripRepository.findAll();
    }

    @Override
    public Trip updateTrip(int id, Trip trip) {
        log.info("Saving trip with data {}", trip);
       Trip tripDb = this.searchById(id);
        if(trip == tripDb){
            log.error("Contienen la misma información");
        }
        trip.setId(id);
        return tripRepository.save(trip);
    }

    @Override
    public Trip addPassenger(int idTrip, int idPassenger) {
        Trip trip = this.searchById(idTrip);
        if(trip.getPassengers().size() >= 3){
            log.error("The trip is full of passengers");
        }else {
            Client passenger = clientService.searchById(idPassenger);
            trip.getPassengers().add(passenger);
            tripRepository.save(trip);
        }
        return this.searchById(idTrip);
    }

    @Override
    public int countPassenger(int id) {
        Trip trip = this.searchById(id);
        return trip.getPassengers().size();
    }

    @Override
    public void updateStatus(int idTrip, boolean status) {
        Trip trip = this.searchById(idTrip);
        trip.setStatus(status);
        tripRepository.save(trip);
    }
}
