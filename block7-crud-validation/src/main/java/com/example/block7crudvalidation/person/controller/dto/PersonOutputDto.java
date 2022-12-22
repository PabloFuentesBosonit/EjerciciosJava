package com.example.block7crudvalidation.person.controller.dto;

import lombok.Data;

import java.net.URL;
import java.util.Date;

@Data
public class PersonOutputDto {
    private int id_person;
    private String usuario;
    private String name;
    private String surname;
    private String personal_email;
    private String company_email;
    private String city;
    private Boolean active;
    private URL image_url;
}
