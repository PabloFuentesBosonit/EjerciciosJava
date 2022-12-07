package com.example.facturaJPA.factura.repository;

import com.example.facturaJPA.factura.domain.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaRepository extends JpaRepository<Factura, Integer> {
}
