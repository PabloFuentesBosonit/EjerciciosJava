package com.example.facturaJPA.factura.domain;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "Facturas")
@NoArgsConstructor
public class Factura {

    @Id
    @GeneratedValue
    @Column(name = "id_factura")
    private int id;

    @Column
    private double importeFactura;

    /*
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

     */

    /*
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "lineas_fra")
    private List<Linea> lineas;

     */
}
