package com.example.facturaJPA.cliente.repository;

import com.example.facturaJPA.cliente.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository <Cliente, Integer> {
}
