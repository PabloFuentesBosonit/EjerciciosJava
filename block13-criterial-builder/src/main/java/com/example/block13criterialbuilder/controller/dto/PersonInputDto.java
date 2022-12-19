package com.example.block13criterialbuilder.controller.dto;

import lombok.Data;

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
    private Boolean active;
    private Date created;
}
