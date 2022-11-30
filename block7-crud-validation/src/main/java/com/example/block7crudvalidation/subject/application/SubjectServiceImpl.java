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
import java.util.Optional;

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

    //añadir estudiante a asignatura
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
        response.setStudents(asignaturasOutput);
        return response;
    }

    @Override
    public SubjectOutputDto getSubjectById(int id) {
        Optional<Subject> subject = subjectRepository.findById(id);
        return SubjectMapper.Instance.subjectToSubjectOutputDTO(subject.get());
    }

    @Override
    public void deleteSubjectById(int id) {
        subjectRepository.findById(id).orElseThrow();
        subjectRepository.deleteById(id);
    }

    @Override
    public Iterable<SubjectOutputDto> getAllSubject(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return subjectRepository.findAll()
                .stream()
                .map(subject -> SubjectMapper.Instance.subjectToSubjectOutputDTO(subject)).toList();
    }

    @Override
    public SubjectOutputDto updateSubject(SubjectInputDto subject, int id) throws Exception {
        Optional<Subject> subjectDb = subjectRepository.findById(id);
        Subject subjectInput = SubjectMapper.Instance.subjectInputDTOToSubject(subject);
        Boolean isEqual = Objects.equals(subjectDb, subjectInput);
        if(isEqual){
            throw new Exception();
        }
        subjectRepository.save(subjectInput);
        return SubjectMapper.Instance.subjectToSubjectOutputDTO(subjectInput);
    }
}
