package com.example.facturaJPA.linea.domain;

import com.example.facturaJPA.factura.domain.Factura;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Linea {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String producto;

    @Column
    private double cantidad;

    @Column
    private double importe;

    @ManyToOne //Hasta que punto lo necesito ?
    private Factura factura;

}
