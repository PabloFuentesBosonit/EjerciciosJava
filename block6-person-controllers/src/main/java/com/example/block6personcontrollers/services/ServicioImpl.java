package com.example.block6personcontrollers.services;
import com.example.block6personcontrollers.clases.Ciudad;
import com.example.block6personcontrollers.clases.Persona;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServicioImpl implements Servicio {

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


