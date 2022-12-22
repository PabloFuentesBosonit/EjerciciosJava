package com.example.block7crudvalidation.person.controller.dto;

import lombok.Data;
import java.net.URL;
import java.util.Date;

@Data
public class PersonInputDto {
    private String usuario;
    private String password;
    private String name;
    private String surname;
    private String company_email;
    private String personal_email;
    private String city;
    private URL image_url;
    private Boolean active;
}

// he quitado para el ejercicio del testing puede que afectara a otro ejercicio.
    /*
    private Date created_date;
    private Date termination_date;
    */