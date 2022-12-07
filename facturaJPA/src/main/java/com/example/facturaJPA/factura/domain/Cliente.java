package com.example.facturaJPA.factura.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Cliente")
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @Column(name="id_cliente")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="nombre", nullable = false, length = 100)
    private String nombre;

    // Un cliente tiene varias facturas y una factura tiene una sola entidad.
}
