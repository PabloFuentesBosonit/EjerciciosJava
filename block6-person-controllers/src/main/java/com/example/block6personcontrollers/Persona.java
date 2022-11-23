package com.example.block6personcontrollers;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//metodos
@Setter
@Getter
@ToString

public class Persona {
    //atributos
    String name;
    int age;
    String town;

    //constructores
    public Persona(String name, int age, String town) {
        this.name = name;
        this.age = age;
        this.town = town;
    }

    public Persona() {

    }
}
