package com.example.block17springbatch.infrastructure.repository;

import com.example.block17springbatch.domain.TiempoRiesgo;
import com.example.block17springbatch.infrastructure.dto.TiempoRiesgoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TiempoRiesgoRepository extends JpaRepository<TiempoRiesgo, Integer> {

    @Query(value = "SELECT localidad, YEAR(fecha) as anio, MONTH(fecha) as mes, COUNT(temperatura) as numeroMediciones, " +
            "AVG(temperatura) as temperaturaMedia FROM tiempo GROUP BY localidad,anio,mes", nativeQuery = true)
    List<TiempoRiesgoDto> findListTiempoRiesgo();
}
