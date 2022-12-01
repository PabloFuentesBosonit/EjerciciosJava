package com.example.block7crudvalidation.profesor.controller.dto;

import com.example.block7crudvalidation.person.controller.dto.PersonOutputDto;
import com.example.block7crudvalidation.student.controller.dto.StudentOutputDto;
import com.example.block7crudvalidation.subject.controller.dto.SubjectOutputDto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ProfesorOutputDto {
    PersonOutputDto person;
    int id_profesor;
    String comments;
    String brand;
    List<StudentOutputDto> student;
}
