package com.example.block6personcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/controlador1")
 public class Controlador1 {
    @Autowired
    Servicio servicio;

    @GetMapping("/addPersona")
    public Persona crearPersona (@RequestHeader(value="name")String name, @RequestHeader(value="age")int age,
                                 @RequestHeader(value="town")String town){

        return servicio.createPersona(name,age,town);
    }

    @PostMapping("/addCiudad")
    void crearCiudad (@RequestBody Ciudad newTown){
        servicio.anadirCiudad(newTown);

    }
}


