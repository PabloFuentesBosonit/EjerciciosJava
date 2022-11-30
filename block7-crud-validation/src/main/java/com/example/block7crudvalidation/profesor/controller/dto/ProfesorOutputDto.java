package com.example.block7crudvalidation.profesor.controller.dto;

import com.example.block7crudvalidation.person.controller.dto.PersonOutputDto;
import com.example.block7crudvalidation.student.controller.dto.StudentOutputDto;
import com.example.block7crudvalidation.subject.controller.dto.SubjectOutputDto;
import lombok.Data;

import java.util.List;

@Data
public class ProfesorOutputDto {
    PersonOutputDto person;
    List<StudentOutputDto> students;
    List<SubjectOutputDto> subjects;
    int id_profesor;
    String comments;
    String brand;

}
