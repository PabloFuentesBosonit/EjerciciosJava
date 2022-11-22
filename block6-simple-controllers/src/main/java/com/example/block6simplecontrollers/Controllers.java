package com.example.block6simplecontrollers;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controllers {

    @GetMapping("/user/{nombre}")
    public String saludar(@PathVariable String nombre){

        return "Hola "+nombre;
    }

    @PostMapping("/useradd")
    public Persona persona (@RequestBody Persona info){
        Persona persona1 = info;
        persona1.setAge(persona1.getAge() + 1);
        return persona1;
    }
}

