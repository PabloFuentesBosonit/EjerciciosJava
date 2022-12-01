package com.example.block7crudvalidation.profesor.application;

import com.example.block7crudvalidation.profesor.controller.dto.ProfesorInputDto;
import com.example.block7crudvalidation.profesor.controller.dto.ProfesorOutputDto;
import com.example.block7crudvalidation.profesor.controller.dto.ProfesorSimpleOutputDto;
import com.example.block7crudvalidation.student.controller.dto.StudentOutputDto;

import java.util.List;

public interface ProfesorService {

    ProfesorOutputDto addProfesor(ProfesorInputDto profesor);
    ProfesorOutputDto getProfesorById(int id);
    void deleteProfesorById(int id);
    Iterable<ProfesorOutputDto> getAllProfesor(int pageNumber, int pageSize);
    ProfesorOutputDto updateProfesor(ProfesorInputDto profesor, int id) throws Exception;
    ProfesorOutputDto addStudentToProfesor(int student_id, int profesor_id);

    List<StudentOutputDto> allStudentToProfesor(int id);

    ProfesorSimpleOutputDto getProfesorSimpleById(int id);
}
