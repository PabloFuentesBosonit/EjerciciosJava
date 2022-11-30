package com.example.block7crudvalidation.person.controller.dto;

import lombok.Data;
import java.net.URL;

@Data
public class PersonInputDto {
    private String user;
    private String password;
    private String name;
    private String surname;
    private String companyEmail;
    private String email;
    private String city;
    private Boolean active;
    private URL imageUrl;
}
