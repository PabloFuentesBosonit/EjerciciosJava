package com.example.block7crud.controller;

import com.example.block7crud.application.PersonServiceImpl;
import com.example.block7crud.controller.dto.PersonInputDto;
import com.example.block7crud.controller.dto.PersonOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/person")
public class Controller {

    @Autowired
    PersonServiceImpl personService;

    @PostMapping
    public ResponseEntity<PersonOutputDto> addPerson(@RequestBody PersonInputDto person){
        return ResponseEntity.ok().body(personService.addPerson(person));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonOutputDto> getPersonById(@PathVariable int id){
        try{
            return ResponseEntity.ok().body(personService.getPersonById(id));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deletePersonByID(@RequestParam int id){
        try{
            personService.deletePersonById(id);
            return ResponseEntity.ok().body("Person with id: "+id+" vas deleted");
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public Iterable<PersonOutputDto> getAllPerson(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize) {

        return personService.getAllPerson(pageNumber, pageSize);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonOutputDto> update(@RequestParam int id, @RequestBody PersonInputDto person) {
        try {
            return  ResponseEntity.ok().body(personService.updatePerson(person, id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}

