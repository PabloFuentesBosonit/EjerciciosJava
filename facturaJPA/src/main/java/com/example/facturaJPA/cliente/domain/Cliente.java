package com.example.facturaJPA.cliente.domain;

import com.example.facturaJPA.factura.domain.Factura;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Cliente")
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @Column(name="id_cliente")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="nombre", nullable = false, length = 100)
    private String nombre;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Factura> facturas = new ArrayList<>();

    public Cliente(String nombre){
        this.id=id;
        this.nombre=nombre;
    }
}
