package com.example.block7crudvalidation.subject.application;

import com.example.block7crudvalidation.student.controller.dto.StudentMapper;
import com.example.block7crudvalidation.student.controller.dto.StudentOutputDto;
import com.example.block7crudvalidation.student.domain.Student;
import com.example.block7crudvalidation.student.repository.StudentRepository;
import com.example.block7crudvalidation.subject.controller.dto.SubjectInputDto;
import com.example.block7crudvalidation.subject.controller.dto.SubjectMapper;
import com.example.block7crudvalidation.subject.controller.dto.SubjectOutputDto;
import com.example.block7crudvalidation.subject.domain.Subject;
import com.example.block7crudvalidation.subject.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    StudentRepository studentRepository;

    @Override
    public SubjectOutputDto addSubject(SubjectInputDto subject) {
        Subject asignatura = SubjectMapper.Instance.subjectInputDTOToSubject(subject);
        Subject asignaturaDb = subjectRepository.save(asignatura);
        return SubjectMapper.Instance.subjectToSubjectOutputDTO(asignaturaDb);
    }

    //Añadir estudiante a asignatura
    @Override
    public SubjectOutputDto addStudentToSubject(int student_id, int subject_id){
        Student studentDb = studentRepository.findById(student_id).orElseThrow();
        Subject subjectDb = subjectRepository.findById(subject_id).orElseThrow();

        studentDb.getSubjectStudent().add(subjectDb);
        subjectDb.getStudentsSubject().add(studentDb);

        subjectRepository.save(subjectDb);
        studentRepository.save(studentDb);

        List<StudentOutputDto> asignaturasOutput = subjectDb.getStudentsSubject()
                .stream()
                .map(StudentMapper.Instance::studentToStudentOutputDTO).toList();


        SubjectOutputDto response = SubjectMapper.Instance.subjectToSubjectOutputDTO(subjectDb);
        response.setStudentsSubject(asignaturasOutput);
        return response;
    }

    @Override
    public SubjectOutputDto getSubjectById(int id) {
        Subject subject = subjectRepository.findById(id).orElseThrow();
        List<StudentOutputDto> estudiantesOutput = subject.getStudentsSubject()
                .stream()
                .map(StudentMapper.Instance::studentToStudentOutputDTO).toList();

        SubjectOutputDto response = SubjectMapper.Instance.subjectToSubjectOutputDTO(subject);
        response.setStudentsSubject(estudiantesOutput);
        return response;
    }

    @Override
    public void deleteSubjectById(int id) {
        Subject subject = subjectRepository.findById(id).orElseThrow();
        if(subject.getStudentsSubject() != null){
            subject.getStudentsSubject()
                    .stream()
                    .forEach(student -> student.setSubjectStudent(null));
            subject.setStudentsSubject(null);
            subjectRepository.save(subject);
        }
        studentRepository.deleteById(id);
    }

    @Override
    public Iterable<SubjectOutputDto> getAllSubject(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return subjectRepository.findAll()
                .stream()
                .map(subject -> SubjectMapper.Instance.subjectToSubjectOutputDTO(subject)).toList();
    }

    // Mostrar todos las asignaturas de un estudiante
    @Override
    public List<StudentOutputDto> allStudentToSubject(int id){
        Subject subject = subjectRepository.findById(id).orElseThrow();
        List<StudentOutputDto> lista = subject.getStudentsSubject()
                .stream()
                .map(StudentMapper.Instance::studentToStudentOutputDTO).toList();
        return lista;
    }

    @Override
    public SubjectOutputDto updateSubject(SubjectInputDto subject, int id) throws Exception {
        Subject subjectDb = subjectRepository.findById(id).orElseThrow();
        Subject subjectInput = SubjectMapper.Instance.subjectInputDTOToSubject(subject);
        Boolean isEqual = Objects.equals(subjectDb, subjectInput);
        if(isEqual){
            throw new Exception("No hay ningún cambio");
        }
        subjectRepository.save(subjectInput);
        return SubjectMapper.Instance.subjectToSubjectOutputDTO(subjectInput);
    }
}
