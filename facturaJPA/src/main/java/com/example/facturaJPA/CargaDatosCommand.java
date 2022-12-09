package com.example.facturaJPA;

import com.example.facturaJPA.cliente.domain.Cliente;
import com.example.facturaJPA.cliente.repository.ClienteRepository;

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


    public void run(String... args) throws Exception {

        int num1 = 10;
        int num2 = 15;

        Cliente cliente = new Cliente();
        cliente.setNombre("Pablo");
        clienteRepository.save(cliente);

        Factura factura = new Factura();
        factura.setImporteFactura(num1 + num2);
        facturaRepository.save(factura);

        Linea linea = new Linea();
        linea.setCantidad(5);
        linea.setImporte(num1);
        linea.setProducto("Champú");

        Linea linea2= new Linea();
        linea2.setCantidad(7);
        linea2.setImporte(num2);
        linea2.setProducto("Galletas");

        List<Linea> lineas = lineaRepository.saveAll(Arrays.asList(linea,linea2));

        factura.setLineas(lineas);
        factura.setCliente(cliente);

        facturaRepository.save(factura);

        cliente.getFacturas().add(factura);
        clienteRepository.save(cliente);
    }
}
