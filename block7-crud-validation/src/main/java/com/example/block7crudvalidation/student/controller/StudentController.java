package com.example.block7crudvalidation.student.controller;

import com.example.block7crudvalidation.student.application.StudentService;
import com.example.block7crudvalidation.student.controller.dto.StudentInputDto;
import com.example.block7crudvalidation.student.controller.dto.StudentOutputDto;
import com.example.block7crudvalidation.subject.controller.dto.SubjectOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController{

    @Autowired
    StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentOutputDto> addStudent(@RequestBody StudentInputDto student){
            return ResponseEntity.ok(studentService.addStudent(student));
    }

    //añadir asignatura
    @PostMapping("/addSubject")
    public ResponseEntity<StudentOutputDto>addSubjectToStudent(@RequestParam int subject_id, @RequestParam int student_id){
        try{
            return ResponseEntity.ok().body(studentService.addSubjectToStudent(subject_id, student_id));
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/template/{id}")
    public StudentOutputDto getStudentByIdTemplate(@PathVariable int id) {
        ResponseEntity<StudentOutputDto> template =  new RestTemplate().getForEntity("http://localhost:8080/student/"+id,
                StudentOutputDto.class);

            if (template.getStatusCode()== HttpStatus.ACCEPTED.OK)
                return template.getBody();
            throw new RuntimeException("The server didn't respond OK");
        }


    @GetMapping("/{id}")
    public ResponseEntity<StudentOutputDto> getStudentById(@PathVariable int id) {
        try {
            return ResponseEntity.ok().body(studentService.getStudentById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    //mostrar todos las asignaturas de un estudiante
    @GetMapping("/asignaturas/{id}")
    public List<SubjectOutputDto> allSubjectToStudent(@PathVariable int id){
        return studentService.allSubjectToStudent(id);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteStudentById(@RequestParam int id) {
        try {
            studentService.deleteStudentById(id);
            return ResponseEntity.ok().body("student with id: "+id+" was deleted");
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public Iterable<StudentOutputDto> getAllStudents(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize) {

        return studentService.getAllStudents(pageNumber, pageSize);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentOutputDto> updateStudent(@RequestBody StudentInputDto student, @PathVariable int id) {
        try{
            return ResponseEntity.ok().body(studentService.updateStudent(student, id));
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

}


