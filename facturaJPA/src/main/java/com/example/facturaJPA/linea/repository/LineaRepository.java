package com.example.facturaJPA.linea.repository;

import com.example.facturaJPA.linea.domain.Linea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LineaRepository extends JpaRepository<Linea, Integer> {
}
