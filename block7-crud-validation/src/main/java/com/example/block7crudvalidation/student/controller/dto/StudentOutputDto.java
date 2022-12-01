package com.example.block7crudvalidation.student.controller.dto;

import com.example.block7crudvalidation.person.controller.dto.PersonOutputDto;
import com.example.block7crudvalidation.student.domain.Student;
import com.example.block7crudvalidation.subject.controller.dto.SubjectOutputDto;
import com.example.block7crudvalidation.subject.domain.Subject;
import lombok.*;

import java.util.List;


@Setter
@Getter
public class StudentOutputDto {
    int id_student;
    PersonOutputDto person;
    List<SubjectOutputDto> subjectStudent;
    int numHours;
    String comments;
    String branch;

}
