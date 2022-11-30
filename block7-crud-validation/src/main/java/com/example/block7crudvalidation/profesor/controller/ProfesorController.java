package com.example.block7crudvalidation.profesor.controller;

import com.example.block7crudvalidation.profesor.application.ProfesorService;
import com.example.block7crudvalidation.profesor.controller.dto.ProfesorInputDto;
import com.example.block7crudvalidation.profesor.controller.dto.ProfesorOutputDto;
import com.example.block7crudvalidation.student.controller.dto.StudentOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profesor")
public class ProfesorController {
    @Autowired
    ProfesorService profesorService;

    @PostMapping
    public ResponseEntity<ProfesorOutputDto>addProfesor(@RequestBody ProfesorInputDto profesor){
         return ResponseEntity.ok(profesorService.addProfesor(profesor));
    }

    //añadir estudiante
    @PostMapping("/addStudent")
    public ResponseEntity<ProfesorOutputDto>addStudentToProfesor(@RequestParam int student_id, @RequestParam int profesor_id){
        try {
            return ResponseEntity.ok().body(profesorService.addStudentToProfesor(student_id,profesor_id));
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfesorOutputDto> getProfesorById(@PathVariable int id){
        try{
            return ResponseEntity.ok().body(profesorService.getProfesorById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    //mostrar todos los estudiantes de un profesor
    @GetMapping("/estudiantes/{id}")
    public List<StudentOutputDto> allStudentToProfesor(@PathVariable int id){
        return profesorService.allStudentToProfesor(id);
    }


    @DeleteMapping
    public ResponseEntity<String> deleteProfesorById(@RequestParam int id) {
        try {
            profesorService.deleteProfesorById(id);
            return ResponseEntity.ok().body("profesor with id "+id+" was deleted");
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping
    public Iterable<ProfesorOutputDto> getAllProfesor(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize) {
        return profesorService.getAllProfesor(pageNumber,pageSize);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfesorOutputDto> updateProfesor(@RequestBody ProfesorInputDto profesor, @PathVariable int id){
        try{
            return ResponseEntity.ok().body(profesorService.updateProfesor(profesor, id));
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }



}
