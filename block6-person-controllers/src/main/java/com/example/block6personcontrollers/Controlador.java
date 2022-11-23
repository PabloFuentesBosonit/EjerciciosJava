package com.example.block6personcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/controlador")
public class Controlador {

    @Autowired
    @Qualifier("bean1")
    Persona persona1;

    @Autowired
    @Qualifier("bean2")
    Persona persona2;

    @Autowired
    @Qualifier("bean3")
    Persona persona3 ;

    @GetMapping("/bean/{bean}")
    public Persona getBean(@PathVariable String bean) {

        if (bean.equalsIgnoreCase("bean1")) {
            return persona1;
        }
        else if(bean.equalsIgnoreCase("bean2")){
            return persona2;
        }
        else if(bean.equalsIgnoreCase("bean3")){
            return persona3;
        } else {
            return null;
        }
    }
}

