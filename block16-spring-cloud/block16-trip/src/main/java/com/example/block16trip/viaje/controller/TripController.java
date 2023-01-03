package com.example.block16trip.viaje.controller;

import com.example.block16trip.viaje.application.TripService;

import com.example.block16trip.viaje.controller.dto.TripInputDto;
import com.example.block16trip.viaje.controller.dto.TripOutputDto;
import com.example.block16trip.viaje.domain.Trip;
import com.example.block16trip.viaje.mapper.TripMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class TripController {

    @Autowired
    TripService tripService;


    @GetMapping("/trip/{id}")
    public TripOutputDto findTrip (@PathVariable int id){
        Trip trip = tripService.searchById(id);
        return TripMapper.Instance.tripToTripOutputDto(trip);
    }

    @GetMapping("/trips")
    public List<TripOutputDto> allClient() {

        List<Trip> trips = tripService.findAllTrips();

        return trips
                .stream()
                .map(TripMapper.Instance::tripToTripOutputDto)
                .toList();
    }

    @GetMapping("/countPassenger/{idTrip}")
    public ResponseEntity<String> countPassenger (@PathVariable int idTrip){
        int number = tripService.countPassenger(idTrip);
        return ResponseEntity.ok().body("The trip with id "+idTrip+" has "+number+" passenger");
    }

    @PostMapping("/addTrip")
    public TripOutputDto addTrip (@RequestBody TripInputDto tripInputDto){
        Trip trip = TripMapper.Instance.tripInputDtoToTrip(tripInputDto);
        tripService.addTrip(trip);
        return TripMapper.Instance.tripToTripOutputDto(trip);
    }

    @PostMapping("/addPassenger/{idTrip}/{idPassenger}")
    public TripOutputDto addPassenger (@PathVariable int idTrip, @PathVariable int idPassenger) throws Error{
        Trip trip = tripService.addPassenger(idTrip, idPassenger);
        return TripMapper.Instance.tripToTripOutputDto(trip);
    }


    @DeleteMapping("/deletedTrip/{id}")
    public ResponseEntity<String> deletedById(@PathVariable int id){
        tripService.deletedTrip(id);
        return ResponseEntity.ok().body("Trip with "+id+" has been successfully removed ");
    }

    @PutMapping("/updateTrip/{id}")
    public TripOutputDto updateTrip (@PathVariable int id, @RequestBody TripInputDto tripInputDto){
        Trip trip = TripMapper.Instance.tripInputDtoToTrip(tripInputDto);
        return TripMapper.Instance.tripToTripOutputDto(tripService.updateTrip(id, trip));
    }

    @PutMapping("/updateStatus/{idTrip}/{status}")

    public ResponseEntity<String> updateStatus (@PathVariable int idTrip, @PathVariable boolean status){
        tripService.updateStatus(idTrip, status);
        return ResponseEntity.ok().body("The status of trip with id "+idTrip+" has changed");
    }


}

