package com.example.block7crudvalidation.profesor.controller.dto;

import com.example.block7crudvalidation.person.controller.dto.PersonInputDto;
import lombok.Data;

@Data
public class ProfesorInputDto {
    PersonInputDto person;
    String comments;
    String brand;
}
