package com.example.block7crudvalidation.subject.controller;

import com.example.block7crudvalidation.student.controller.dto.StudentOutputDto;
import com.example.block7crudvalidation.subject.controller.dto.SubjectInputDto;
import com.example.block7crudvalidation.subject.controller.dto.SubjectOutputDto;
import com.example.block7crudvalidation.subject.application.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {
    @Autowired
    SubjectService subjectService;

    @PostMapping
    public ResponseEntity<SubjectOutputDto> addSubject(@RequestBody SubjectInputDto subject) throws Exception{
      try {
          return ResponseEntity.ok(subjectService.addSubject(subject));
      } catch (Exception e) {
          throw new Exception();
      }
    }

    // añadir estudiante
    @PostMapping("/addSubject")
    public ResponseEntity<SubjectOutputDto>addSubjectToStudent(@RequestParam int student_id, @RequestParam int subject_id){
        try{
            return ResponseEntity.ok().body(subjectService.addStudentToSubject(subject_id, student_id));
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectOutputDto> getSubjectById(@PathVariable int id) {
        try{
            return ResponseEntity.ok().body(subjectService.getSubjectById(id));
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }

    }

    //mostrar todos los estudiantes de una asignatura
    @GetMapping("/estudiantes/{id}")
    public List<StudentOutputDto> allStudentToSubject(@PathVariable int id){
        return subjectService.allStudentToSubject(id);
    }


    @DeleteMapping
    public ResponseEntity<String> deleteSubjectById(@RequestParam int id) {
        try {
            subjectService.deleteSubjectById(id);
            return ResponseEntity.ok().body("person with id: "+id+" was deleted");
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public Iterable<SubjectOutputDto> getAllPersons(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize) {
        return subjectService.getAllSubject(pageNumber, pageSize);
    }

    @ResponseStatus
    @PutMapping("/{id}")
    public ResponseEntity<SubjectOutputDto> updatePerson(@RequestBody SubjectInputDto subject, @PathVariable int id)
            throws Exception {
        return ResponseEntity.ok(subjectService.updateSubject(subject, id));
    }

}
