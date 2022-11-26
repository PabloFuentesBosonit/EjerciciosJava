package com.example.block7crudvalidation.controller.dto;

import lombok.Data;

import java.net.URL;
import java.util.Date;

@Data

public class PersonOutputDto {
    private int id;
    private String user;
    private String name;
    private String surname;
    private String email;
    private String companyEmail;
    private String city;
    private Boolean active;
    private Date createdAt;
    private Date updatedAt;
    private URL imageUrl;
    private Date terminationDate;

}
