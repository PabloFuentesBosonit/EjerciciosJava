package com.example.block17springbatch.repository;

import com.example.block17springbatch.domain.TiempoRiesgo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TiempoRiesgoRepository extends JpaRepository<TiempoRiesgo, Integer> {
    @Query(value = "SELECT localidad, anio, COUNT(temperatura) as numeroMediciones, AVG(temperatura) as temperaturaMedia " +
            "FROM tiempo_Riesgo GROUP BY localidad, anio", nativeQuery = true)
    List<TiempoRiesgo> findListTiempoRiesgo();

}
