package com.example.block6personcontrollers.clases;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//metodos
@Setter
@Getter
@ToString

public class Ciudad {
    //atributos
    String name;
    int numberPeople;

    //constructores
    public Ciudad (String name, int numberPeople){
        this.name = name;
        this.numberPeople = numberPeople;
    }


}


