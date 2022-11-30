package com.example.block7crudvalidation.profesor.application;

import com.example.block7crudvalidation.profesor.domain.Profesor;
import com.example.block7crudvalidation.profesor.controller.dto.ProfesorInputDto;
import com.example.block7crudvalidation.profesor.controller.dto.ProfesorMapper;
import com.example.block7crudvalidation.profesor.controller.dto.ProfesorOutputDto;
import com.example.block7crudvalidation.profesor.repository.ProfesorRepository;
import com.example.block7crudvalidation.student.controller.dto.StudentMapper;
import com.example.block7crudvalidation.student.controller.dto.StudentOutputDto;
import com.example.block7crudvalidation.student.domain.Student;
import com.example.block7crudvalidation.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProfesorServiceImpl implements ProfesorService {
    @Autowired
    ProfesorRepository profesorRepository;

    @Autowired
    StudentRepository studentRepository;

    @Override
    public ProfesorOutputDto addProfesor(ProfesorInputDto profesor) {
        Profesor profesor1 = ProfesorMapper.Instance.profesorInputDTOToProfesor(profesor);
        Profesor profesorDb = profesorRepository.save(profesor1);
        return ProfesorMapper.Instance.profesorToProfesorOutputDTO(profesorDb);
    }

    //añadir estudiante a profesor
    @Override
    public ProfesorOutputDto addStudentToProfesor(int student_id, int profesor_id) {
        Profesor profesorDb = profesorRepository.findById(profesor_id).orElseThrow();
        Student studentDb = studentRepository.findById(student_id).orElseThrow();

        studentDb.setProfesor(profesorDb);
        profesorDb.getStudentsProfesor().add(studentDb);

        studentRepository.save(studentDb);
        profesorRepository.save(profesorDb);

        List<StudentOutputDto> estudiantesOutput = profesorDb.getStudentsProfesor()
                .stream()
                .map(StudentMapper.Instance::studentToStudentOutputDTO).toList();

        ProfesorOutputDto response = ProfesorMapper.Instance.profesorToProfesorOutputDTO(profesorDb);
        response.setStudents(estudiantesOutput);
        return response;
    }

    @Override
    public ProfesorOutputDto getProfesorById(int id) {
        Profesor profesor = profesorRepository.findById(id).orElseThrow();
        List<StudentOutputDto> estudianteOutput = profesor.getStudentsProfesor()
                .stream()
                .map(StudentMapper.Instance::studentToStudentOutputDTO).toList();

        ProfesorOutputDto response = ProfesorMapper.Instance.profesorToProfesorOutputDTO(profesor);
        response.setStudents(estudianteOutput);
        return response;
    }

    @Override
    public void deleteProfesorById(int id) {
        profesorRepository.findById(id).orElseThrow();
        profesorRepository.deleteById(id);

    }

    @Override
    public Iterable<ProfesorOutputDto> getAllProfesor(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return profesorRepository.findAll()
                .stream()
                .map(profesor -> ProfesorMapper.Instance.profesorToProfesorOutputDTO(profesor)).toList();
    }

    // mostrar todos los estudiantes de un profesor
    @Override
    public List<StudentOutputDto> allStudentToProfesor(int id){
        Profesor profesor = profesorRepository.findById(id).orElseThrow();
        List<StudentOutputDto> lista = profesor.getStudentsProfesor()
                .stream()
                .map(StudentMapper.Instance::studentToStudentOutputDTO).toList();
        return lista;
    }



    @Override
    public ProfesorOutputDto updateProfesor(ProfesorInputDto profesor, int id ) throws Exception {
        Optional<Profesor> profesorDb = profesorRepository.findById(id);
        Profesor profesorInput = ProfesorMapper.Instance.profesorInputDTOToProfesor(profesor);
        Boolean isEqual = Objects.equals(profesorDb, profesorInput);
        if(isEqual){
            throw new Exception();
        }
        profesorRepository.save(profesorInput);
        return ProfesorMapper.Instance.profesorToProfesorOutputDTO(profesorInput);
    }

}
