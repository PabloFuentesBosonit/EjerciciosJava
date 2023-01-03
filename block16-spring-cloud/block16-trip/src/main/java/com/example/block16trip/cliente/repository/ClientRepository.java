package com.example.block16trip.cliente.repository;

import com.example.block16trip.cliente.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository <Client, Integer> {
}
