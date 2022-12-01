package com.example.block7crudvalidation.subject.controller.dto;
import com.example.block7crudvalidation.student.controller.dto.StudentOutputDto;
import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
public class SubjectOutputDto {
    int id_subject;
    List<StudentOutputDto> studentsSubject;
    String subjectName;
    String comment;
    Date initial_date;
}
