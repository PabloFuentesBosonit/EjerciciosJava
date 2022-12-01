package com.example.block7crudvalidation.profesor.controller.dto;

import com.example.block7crudvalidation.person.controller.dto.PersonOutputDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProfesorSimpleOutputDto {
    PersonOutputDto person;
    int id_profesor;
    String comments;
    String brand;
}
