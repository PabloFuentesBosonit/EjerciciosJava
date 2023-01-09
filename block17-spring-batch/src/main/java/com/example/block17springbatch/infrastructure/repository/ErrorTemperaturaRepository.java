package com.example.block17springbatch.infrastructure.repository;

import com.example.block17springbatch.domain.ErrorTemperatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ErrorTemperaturaRepository extends JpaRepository<ErrorTemperatura, Integer> {
}
