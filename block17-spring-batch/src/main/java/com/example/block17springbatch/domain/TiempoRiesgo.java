package com.example.block17springbatch.domain;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class TiempoRiesgo {

    public static final String HIGH = "Altas";
    public static final String LOW = "Medias";
    public static final String NORMAL = "Bajas";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String localidad;
    private String fecha;
    private int temperatura;
    private String anio;
    private String mes;
    private String dia;
    private String riesgo;

    @OneToOne(cascade = CascadeType.ALL)
    Tiempo tiempo;

    public TiempoRiesgo(String localidad, String fecha, int temperatura,
                        String anio, String mes, String dia,
                        String riesgo, Tiempo tiempo) {
        this.localidad = localidad;
        this.fecha = fecha;
        this.temperatura = temperatura;
        this.anio = anio;
        this.mes = mes;
        this.dia = dia;
        this.riesgo = riesgo;
        this.tiempo = tiempo;
    }
}
