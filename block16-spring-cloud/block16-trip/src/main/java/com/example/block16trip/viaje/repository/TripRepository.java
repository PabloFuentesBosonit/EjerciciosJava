package com.example.block16trip.viaje.repository;

import com.example.block16trip.viaje.domain.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip, Integer> {
}
