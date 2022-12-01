package com.example.block7crudvalidation.subject.application;

import com.example.block7crudvalidation.student.controller.dto.StudentOutputDto;
import com.example.block7crudvalidation.subject.controller.dto.SubjectInputDto;
import com.example.block7crudvalidation.subject.controller.dto.SubjectOutputDto;

import java.util.List;

public interface SubjectService {
    SubjectOutputDto addSubject (SubjectInputDto subject);

    //añadir estudiante a asignatura
    SubjectOutputDto addStudentToSubject(int student_id, int subject_id);

    SubjectOutputDto getSubjectById (int id);
    void deleteSubjectById(int id);
    Iterable<SubjectOutputDto> getAllSubject(int pageNumber, int pageSize);

    SubjectOutputDto updateSubject (SubjectInputDto subject, int id) throws Exception;

    // mostrar todos las asignaturas de un estudiante
    List<StudentOutputDto> allStudentToSubject(int id);
}
