package com.example.block6pathvariableheaders;

import lombok.Data;

@Data
public class Persona {
    public int id;
    public String name;
    public String town;

    public Persona(int id, String name, String town){
        this.id = id;
        this.name = name;
        this.town = town;
    }


}
