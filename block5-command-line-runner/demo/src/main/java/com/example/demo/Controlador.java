package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controlador {

    @GetMapping
    public String holaMundo(){
        return "Hola Mundo";
    }

    @GetMapping("/adios")
    public String adiosMundo(){
        return "Adios mundo cruel";
    }

    @GetMapping("/adios/{nombre}")
    public String adiosMundo(@PathVariable String nombre){
        return "Adios mundo fatídico "+nombre;
    }

}
