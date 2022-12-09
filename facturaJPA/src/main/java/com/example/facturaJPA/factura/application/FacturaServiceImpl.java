package com.example.facturaJPA.factura.application;

import com.example.facturaJPA.cliente.controller.dto.ClienteMapper;
import com.example.facturaJPA.cliente.controller.dto.ClienteOutputDto;
import com.example.facturaJPA.cliente.domain.Cliente;
import com.example.facturaJPA.factura.controller.dto.FacturaInputDto;
import com.example.facturaJPA.factura.controller.dto.FacturaMapper;
import com.example.facturaJPA.factura.controller.dto.FacturaOutputDto;
import com.example.facturaJPA.factura.domain.Factura;
import com.example.facturaJPA.factura.repository.FacturaRepository;
import com.example.facturaJPA.linea.controller.dto.LineaInputDto;
import com.example.facturaJPA.linea.controller.dto.LineaMapper;
import com.example.facturaJPA.linea.controller.dto.LineaOutputDto;
import com.example.facturaJPA.linea.domain.Linea;
import com.example.facturaJPA.linea.repository.LineaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FacturaServiceImpl implements FacturaService{
    @Autowired
    FacturaRepository facturaRepository;
    @Autowired
    private LineaRepository lineaRepository;

    @Override
    public FacturaOutputDto addFactura(FacturaInputDto facturaInput) {
        Factura factura = FacturaMapper.Instance.facturaInputDtoToFactura(facturaInput);
        Factura facturaDb = facturaRepository.save(factura);
        return FacturaMapper.Instance.facturaToFacturaOutputDto(facturaDb);
    }

    @Override
    public FacturaOutputDto addLineas(LineaInputDto lineaInputDto, int idFactura) {
        Factura factura = facturaRepository.findById(idFactura).orElseThrow();
        Linea linea = LineaMapper.Instance.lineaInputDtoToLinea(lineaInputDto);

        factura.getLineas().add(linea);
        linea.setFactura(factura);

        Factura facturaNew = actualizaImporte(factura);

        facturaRepository.save(facturaNew);
        lineaRepository.save(linea);

        List<LineaOutputDto> lineasOut = factura.getLineas()
                .stream()
                .map(LineaMapper.Instance::lineaToLineaOutputDto).toList();

        FacturaOutputDto facturaDb = FacturaMapper.Instance.facturaToFacturaOutputDto(factura);
        facturaDb.setLineas(lineasOut);
        return facturaDb;
    }

    @Override
    public FacturaOutputDto getFacturaById(int id) {
        Factura factura = facturaRepository.findById(id).orElseThrow();
        List<LineaOutputDto> lineasOut = factura.getLineas()
                .stream()
                .map(LineaMapper.Instance::lineaToLineaOutputDto).toList();

        Cliente clienteDb = factura.getCliente();
        ClienteOutputDto cliente = ClienteMapper.Instance.clienteToClienteOutputDto(clienteDb);

        FacturaOutputDto facturaDb = FacturaMapper.Instance.facturaToFacturaOutputDto(factura);
        facturaDb.setLineas(lineasOut);
        facturaDb.setCliente(cliente);
        return facturaDb;
    }

    @Override
    public void deleteFacturaById(int id) {
        Factura factura = facturaRepository.findById(id).orElseThrow();
        if(factura.getCliente() != null){
            factura.setCliente(null);
            facturaRepository.save(factura);
        }
        facturaRepository.deleteById(id);
    }

    @Override
    public Iterable<FacturaOutputDto> getAllFacturas(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return facturaRepository.findAll()
                .stream()
                .map(factura -> FacturaMapper.Instance.facturaToFacturaOutputDto(factura)).toList();
    }

    @Override
    public FacturaOutputDto updateFactura(FacturaInputDto facturaInputDto, int id) throws Exception {
        Optional<Factura> facturaDb = facturaRepository.findById(id);
        Factura factura = FacturaMapper.Instance.facturaInputDtoToFactura(facturaInputDto);
        Boolean isEqual = Objects.equals(facturaDb, factura);
        if(isEqual){
            throw new Exception();
        }
        facturaRepository.save(factura);
        return FacturaMapper.Instance.facturaToFacturaOutputDto(factura);
    }

    public Factura actualizaImporte(Factura factura) {
        List<Linea> lineas = factura.getLineas();
        double total = 0;
        for(Linea linea : lineas){
            total = total + linea.getImporte()* linea.getCantidad();
        }
        factura.setImporteFactura(total);
        return factura;
    }

}
