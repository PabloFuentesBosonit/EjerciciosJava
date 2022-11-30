package com.example.block7crudvalidation.student.application;

import com.example.block7crudvalidation.student.controller.dto.StudentMapper;
import com.example.block7crudvalidation.student.repository.StudentRepository;
import com.example.block7crudvalidation.student.controller.dto.StudentInputDto;
import com.example.block7crudvalidation.student.controller.dto.StudentOutputDto;
import com.example.block7crudvalidation.student.domain.Student;
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
public class StudentServiceImpl implements StudentService{

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Override
    public StudentOutputDto addStudent(StudentInputDto student) {
        Student estudiante = StudentMapper.Instance.studentInputDTOToStudent(student);
        Student estudianteDb = studentRepository.save(estudiante);
        return StudentMapper.Instance.studentToStudentOutputDTO(estudianteDb);
    }

    //añadir asignatura a estudiante
    @Override
    public StudentOutputDto addSubjectToStudent(int subject_id, int student_id){
        Student studentDb = studentRepository.findById(student_id).orElseThrow();
        Subject subjectDb = subjectRepository.findById(subject_id).orElseThrow();

        studentDb.getSubjectStudent().add(subjectDb);
        subjectDb.getStudentsSubject().add(studentDb);

        subjectRepository.save(subjectDb);
        studentRepository.save(studentDb);

        List<SubjectOutputDto> asignaturasOutput = studentDb.getSubjectStudent()
                .stream()
                .map(SubjectMapper.Instance::subjectToSubjectOutputDTO).toList();


        StudentOutputDto response = StudentMapper.Instance.studentToStudentOutputDTO(studentDb);
        response.setSubjects(asignaturasOutput);
        return response;
    }


    @Override
    public StudentOutputDto getStudentById(int id) {
        Optional<Student> student = studentRepository.findById(id);
        return StudentMapper.Instance.studentToStudentOutputDTO(student.get());
    }

    @Override
    public void deleteStudentById(int id) {
        studentRepository.findById(id).orElseThrow();
        studentRepository.deleteById(id);
    }
    @Override
    public List<StudentOutputDto> getAllStudents(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return studentRepository.findAll()
                .stream()
                .map(student -> StudentMapper.Instance.studentToStudentOutputDTO(student)).toList();
    }
    @Override
    public StudentOutputDto updateStudent(StudentInputDto student, int id) throws Exception {
        Optional<Student> studentDb = studentRepository.findById(id);
        Student studentInput = StudentMapper.Instance.studentInputDTOToStudent(student);
        Boolean isEqual = Objects.equals(studentDb, studentInput);
        if(isEqual){
            throw new Exception();
        }
        studentRepository.save(studentInput);
        return StudentMapper.Instance.studentToStudentOutputDTO(studentInput);
    }
}

