package com.example.block7crudvalidation.student.controller.dto;
import com.example.block7crudvalidation.person.controller.dto.PersonInputDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class StudentInputDto {
    PersonInputDto person;
    int numHours;
    String comments;
    String branch;
}
