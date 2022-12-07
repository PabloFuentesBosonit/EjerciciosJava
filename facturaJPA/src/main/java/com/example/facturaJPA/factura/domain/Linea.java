package com.example.facturaJPA.factura.domain;

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
    private int idFra;

    @Column(nullable = false)
    private String nombreProducto;

    @Column
    private double cantidad;

    @Column
    private double precio;

    // Una factura tiene muchas líneas y muchas líneas tienen una sola factura.
}
