package com.example.block7crudvalidation.student.application;


import com.example.block7crudvalidation.student.controller.dto.StudentInputDto;
import com.example.block7crudvalidation.student.controller.dto.StudentOutputDto;
import com.example.block7crudvalidation.subject.controller.dto.SubjectOutputDto;

import java.util.List;

public interface StudentService {

    StudentOutputDto addStudent(StudentInputDto student);
    StudentOutputDto getStudentById(int id);
    void deleteStudentById( int id);
    Iterable<StudentOutputDto> getAllStudents(int pageNumber, int pageSize);
    StudentOutputDto updateStudent(StudentInputDto student, int id) throws Exception;
    StudentOutputDto addSubjectToStudent(int subject_id, int student_id);
    List<SubjectOutputDto> allSubjectToStudent(int id);
}

