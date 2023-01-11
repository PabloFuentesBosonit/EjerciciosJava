package com.example.block17springbatch.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Tiempo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String localidad;
    private String fecha;
    private int temperatura;

}
