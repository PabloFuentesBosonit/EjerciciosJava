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
    private Date created_date;
    private Boolean active;
    private Date termination_date;
}
