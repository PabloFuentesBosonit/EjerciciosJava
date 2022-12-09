package com.example.facturaJPA;

import com.example.facturaJPA.cliente.domain.Cliente;
import com.example.facturaJPA.cliente.repository.ClienteRepository;

import com.example.facturaJPA.factura.application.FacturaService;
import com.example.facturaJPA.factura.domain.Factura;
import com.example.facturaJPA.factura.repository.FacturaRepository;

import com.example.facturaJPA.linea.domain.Linea;
import com.example.facturaJPA.linea.repository.LineaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CargaDatosCommand implements CommandLineRunner {

    @Autowired
    private ClienteRepository clienteRepository;


    @Autowired
    private LineaRepository lineaRepository;


    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private FacturaService facturaService;


    public void run(String... args) throws Exception {


        Cliente cliente = new Cliente();
        cliente.setNombre("Pablo");
        clienteRepository.save(cliente);

        Factura factura = new Factura();

        Linea linea = new Linea();
        linea.setCantidad(5);
        linea.setImporte(15);
        linea.setProducto("Champú");
        factura.getLineas().add(linea);

        Linea linea2= new Linea();
        linea2.setCantidad(7);
        linea2.setImporte(25);
        linea2.setProducto("Galletas");
        factura.getLineas().add(linea2);


        factura.setCliente(cliente);
        factura.setImporteFactura((linea.getCantidad()*linea.getImporte())
                + (linea2.getCantidad()*linea2.getImporte()));

        facturaRepository.save(factura);

        cliente.getFacturas().add(factura);
        clienteRepository.save(cliente);
    }
}
