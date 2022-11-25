package com.example.block7crudvalidation.controller;

import com.example.block7crudvalidation.application.PersonService;
import com.example.block7crudvalidation.controller.dto.PersonInputDto;
import com.example.block7crudvalidation.controller.dto.PersonOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/person")
public class Controller {
    @Autowired
    PersonService personService;

    //No le llega lo del request body

    @PostMapping
    public ResponseEntity<PersonOutputDto> addPerson(@RequestBody PersonInputDto person) {
        return ResponseEntity.ok(personService.addPerson(person));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonOutputDto> getPersonById(@PathVariable int id) {
        try {
            return ResponseEntity.ok().body(personService.getPersonById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deletePersonById(@RequestParam int id) {
        try {
            personService.deletePersonById(id);
            return ResponseEntity.ok().body("person with id: "+id+" was deleted");
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public Iterable<PersonOutputDto> getAllPersons(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize) {
        return personService.getAllPersons(pageNumber, pageSize);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonOutputDto> updatePerson(@RequestBody PersonInputDto person, @PathVariable int id)
            throws Exception {
        return ResponseEntity.ok(personService.updatePerson(person, id));
    }

}

