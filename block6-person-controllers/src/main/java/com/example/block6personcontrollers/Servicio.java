package com.example.block6personcontrollers;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Servicio {

    public Persona persona = new Persona();

    public Persona createPersona(String name,int age, String town){
        persona.setName(name);
        persona.setAge(age);
        persona.setTown(town);
        return persona;
    }

    public Persona doblarEdad(String name,int age, String town){
        persona.setName(name);
        persona.setAge(age * 2);
        persona.setTown(town);
        return persona;
    }

    public List<Ciudad> towns = new ArrayList<>();

    public void anadirCiudad(Ciudad info){
        towns.add(info);
    }

    public List<Ciudad> mostrarCiudades() {
        return towns;
    }
}


