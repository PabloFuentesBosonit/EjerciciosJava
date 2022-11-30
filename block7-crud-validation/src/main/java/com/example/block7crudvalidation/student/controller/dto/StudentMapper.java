package com.example.block7crudvalidation.student.controller.dto;

import com.example.block7crudvalidation.student.domain.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentMapper {
    StudentMapper Instance = Mappers.getMapper(StudentMapper.class);

    StudentOutputDto studentToStudentOutputDTO(Student subject);

    Student studentInputDTOToStudent(StudentInputDto studentInputDto);
}
