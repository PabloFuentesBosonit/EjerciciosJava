package com.example.block6pathvariableheaders.controladores;


import com.example.block6pathvariableheaders.Persona;
import com.example.block6pathvariableheaders.Servicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


@RestController
public class Controlador {
    @Autowired
    Servicio servicio;

    @PostMapping("/user")
    public Persona incorporarPersona(@RequestBody Persona info){
        return servicio.meterPersona(info);
    }

    @GetMapping("/user/{id}")
    public int mostrarId(@PathVariable int id){
        return id;
    }

    @PutMapping("/post")
    public HashMap obtenerVar(@RequestParam(name= "var1") String var1,
                              @RequestParam(name= "var2") String var2){
        return servicio.meterVar(var1, var2);
    }

}
