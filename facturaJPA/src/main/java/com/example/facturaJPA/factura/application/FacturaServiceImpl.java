package com.example.facturaJPA.factura.application;

import com.example.facturaJPA.factura.controller.dto.FacturaInputDto;
import com.example.facturaJPA.factura.controller.dto.FacturaMapper;
import com.example.facturaJPA.factura.controller.dto.FacturaOutputDto;
import com.example.facturaJPA.factura.domain.Factura;
import com.example.facturaJPA.factura.repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class FacturaServiceImpl implements FacturaService{
    @Autowired
    FacturaRepository facturaRepository;

    @Override
    public FacturaOutputDto addFactura(FacturaInputDto facturaInput) {
        Factura factura = FacturaMapper.Instance.facturaInputDtoToFactura(facturaInput);
        Factura facturaDb = facturaRepository.save(factura);
        return FacturaMapper.Instance.facturaToFacturaOutputDto(facturaDb);
    }

    @Override
    public FacturaOutputDto getFacturaById(int id) {
        Optional<Factura> person = facturaRepository.findById(id);
        return FacturaMapper.Instance.facturaToFacturaOutputDto(person.get());
    }

    @Override
    public void deleteFacturaById(int id) {
        facturaRepository.findById(id).orElseThrow();
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
}
