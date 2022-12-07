package com.example.facturaJPA.linea.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Linea  {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String producto;

    @Column
    private double cantidad;

    @Column
    private double importe;

    // Una factura tiene muchas líneas y muchas líneas tienen una sola factura.
}
