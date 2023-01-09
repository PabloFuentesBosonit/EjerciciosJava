package com.example.block17springbatch.infrastructure.dto;

import lombok.Data;

@Data
public class TiempoRiesgoDto {

    int riesgo;

    String localidad;
    String anio;
    String mes;
    Float temperaturaMedia;
    int numeroMediciones;
}
