package com.example.block17springbatch.domain;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class TiempoRiesgo {

    public static final int HIGH = 3;
    public static final int LOW = 2;
    public static final int NORMAL = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String fecha;
    private int riesgo;

    @OneToOne(cascade = CascadeType.ALL)
    Tiempo tiempo;

    public TiempoRiesgo(String fecha, int riesgo, Tiempo tiempo) {
        this.fecha = fecha;
        this.riesgo = riesgo;
        this.tiempo = tiempo;
    }
}
