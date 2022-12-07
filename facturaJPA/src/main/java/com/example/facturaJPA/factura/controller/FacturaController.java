package com.example.facturaJPA.factura.controller;


import com.example.facturaJPA.factura.application.FacturaServiceImpl;
import com.example.facturaJPA.factura.controller.dto.FacturaInputDto;
import com.example.facturaJPA.factura.controller.dto.FacturaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/factura")
public class FacturaController {
    @Autowired
    FacturaServiceImpl facturaService;

    @PostMapping
    public ResponseEntity<FacturaOutputDto> addFactura(@RequestBody FacturaInputDto facturaInputDto) {
        return ResponseEntity.ok().body(facturaService.addFactura(facturaInputDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacturaOutputDto> getFacturasById(@PathVariable int id) {
        try {
            return ResponseEntity.ok().body(facturaService.getFacturaById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFacturaById(@PathVariable int id) {
        try {
            facturaService.deleteFacturaById(id);
            return ResponseEntity.ok().body("student with id: "+id+" was deleted");
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public Iterable<FacturaOutputDto> getAllFacturas(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize) {
        return facturaService.getAllFacturas(pageNumber, pageSize);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FacturaOutputDto> updateFactura(@RequestBody FacturaInputDto facturaInputDto, @PathVariable int id) {
        try {
            facturaService.getFacturaById(id);
            return  ResponseEntity.ok().body(facturaService.addFactura(facturaInputDto));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}

