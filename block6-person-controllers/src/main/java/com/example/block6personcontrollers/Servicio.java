package com.example.block6personcontrollers;

import java.util.ArrayList;
import java.util.List;

public interface Servicio {
    Persona persona = new Persona();

    Persona createPersona(String name,int age, String town);

    Persona doblarEdad(String name,int age, String town);

    List<Ciudad> towns = new ArrayList<>();

    void anadirCiudad(Ciudad info);

    List<Ciudad> mostrarCiudades();
}
