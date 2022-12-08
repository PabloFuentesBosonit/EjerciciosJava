package com.example.facturaJPA.factura.domain;


import com.example.facturaJPA.cliente.domain.Cliente;
import com.example.facturaJPA.linea.domain.Linea;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Facturas")
@NoArgsConstructor
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_factura")
    private int id;

    @Column
    private double importeFactura;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Linea> lineas = new ArrayList<>();

}
