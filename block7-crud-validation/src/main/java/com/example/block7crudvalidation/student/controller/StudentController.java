package com.example.block7crudvalidation.student.controller;

import com.example.block7crudvalidation.student.application.StudentService;
import com.example.block7crudvalidation.student.controller.dto.StudentInputDto;
import com.example.block7crudvalidation.student.controller.dto.StudentOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<StudentOutputDto> getStudentById(@PathVariable int id) {
        try {
            return ResponseEntity.ok().body(studentService.getStudentById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
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


