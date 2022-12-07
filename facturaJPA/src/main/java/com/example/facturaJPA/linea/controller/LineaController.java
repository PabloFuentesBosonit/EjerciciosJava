package com.example.facturaJPA.linea.controller;


import com.example.facturaJPA.linea.application.LineaService;
import com.example.facturaJPA.linea.controller.dto.LineaInputDto;
import com.example.facturaJPA.linea.controller.dto.LineaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/linea")
public class LineaController {

    @Autowired
    LineaService lineaService;

    @PostMapping
    public ResponseEntity<LineaOutputDto> addLinea(@RequestBody LineaInputDto lineaInputDto) {
        return ResponseEntity.ok().body(lineaService.addLinea(lineaInputDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LineaOutputDto> getLineaById(@PathVariable int id) {
        try {
            return ResponseEntity.ok().body(lineaService.getLineaById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLineaById(@PathVariable int id) {
        try {
            lineaService.deleteLineaById(id);
            return ResponseEntity.ok().body("linea with id: "+id+" was deleted");
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public Iterable<LineaOutputDto> getAllLineas(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize) {
        return lineaService.getAllLineas(pageNumber, pageSize);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LineaOutputDto> updateLinea(@RequestBody LineaInputDto lineaInputDto, @PathVariable int id) {
        try {
            lineaService.getLineaById(id);
            return  ResponseEntity.ok().body(lineaService.addLinea(lineaInputDto));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}

