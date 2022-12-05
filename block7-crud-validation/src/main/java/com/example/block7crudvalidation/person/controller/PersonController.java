package com.example.block7crudvalidation.person.controller;

import com.example.block7crudvalidation.person.application.PersonService;
import com.example.block7crudvalidation.person.controller.dto.PersonInputDto;
import com.example.block7crudvalidation.person.controller.dto.PersonOutputDto;
import com.example.block7crudvalidation.person.exception.EntityNotFoundException;
import com.example.block7crudvalidation.person.exception.UnprocessableEntityException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/person")
public class PersonController {
    @Autowired
    PersonService personService;

    @ResponseStatus
    @PostMapping
    public ResponseEntity<PersonOutputDto> addPerson(@RequestBody PersonInputDto person) {
        try {
            return ResponseEntity.ok(personService.addPerson(person));
        } catch (Exception e) {
            throw new UnprocessableEntityException("Entidad no procesable");
        }
    }

    @ResponseStatus
    @GetMapping("/{id}")
    public ResponseEntity<PersonOutputDto> getPersonById(@PathVariable int id) {
        try{
            return ResponseEntity.ok().body(personService.getPersonById(id));
        }catch (Exception e){
            throw new EntityNotFoundException("Usuario no encontrado");
        }

    }

    @GetMapping("/user/{name}")
    public ResponseEntity<PersonOutputDto> getPersonByName(@PathVariable String name) {
        try {
            return ResponseEntity.ok().body(personService.getPersonByName(name));
        } catch (Exception e) {
            throw new UnprocessableEntityException("Los campos no cumplen los requisitos");
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

    @ResponseStatus
    @PutMapping("/{id}")
    public ResponseEntity<PersonOutputDto> updatePerson(@RequestBody PersonInputDto person, @PathVariable int id)
            throws Exception {
        return ResponseEntity.ok(personService.updatePerson(person, id));
    }

}
