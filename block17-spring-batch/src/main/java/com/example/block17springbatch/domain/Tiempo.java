package com.example.block17springbatch.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Tiempo {
    @Id
    @GeneratedValue
    private int id;

    private String localidad;
    private String fecha;
    private int temperatura;

    @OneToOne
    int idTiempoRiesgo;

}
