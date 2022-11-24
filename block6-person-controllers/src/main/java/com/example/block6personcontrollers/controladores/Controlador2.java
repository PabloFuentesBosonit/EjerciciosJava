package com.example.block6personcontrollers.controladores;

import com.example.block6personcontrollers.clases.Ciudad;
import com.example.block6personcontrollers.clases.Persona;
import com.example.block6personcontrollers.services.Servicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/controlador2")
public class Controlador2 {
    @Autowired
    Servicio servicio;

    @GetMapping("/getPersona")
    public Persona doblar (@RequestHeader(value="name")String name, @RequestHeader(value="age")int age,
                           @RequestHeader(value="town")String town){
            return servicio.doblarEdad(name,age,town);
    }

    @GetMapping("/getCiudad")
    public List<Ciudad> mostrar () {
        return servicio.mostrarCiudades();
    }
}