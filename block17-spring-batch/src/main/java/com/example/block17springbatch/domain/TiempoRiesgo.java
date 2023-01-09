package com.example.block17springbatch.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class TiempoRiesgo {
    public static final int HIGH = 3;
    public static final int NORMAL = 2;
    public static final int LOW = 1 ;

    @Id
    @GeneratedValue
    private int id;

    private String fecha;
    int riesgo;

    String localidad;
    String anio;
    String mes;
    Float temperaturaMedia;
    int numeroMediciones;

    @OneToOne
    Tiempo tiempo;
}
