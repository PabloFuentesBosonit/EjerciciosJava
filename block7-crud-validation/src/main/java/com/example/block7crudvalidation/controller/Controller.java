package com.example.block7crudvalidation.controller;

import com.example.block7crudvalidation.application.PersonService;
import com.example.block7crudvalidation.controller.dto.PersonInputDto;
import com.example.block7crudvalidation.controller.dto.PersonOutputDto;
import com.example.block7crudvalidation.exception.EntityNotFoundException;
import com.example.block7crudvalidation.exception.UnprocessableEntityException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/person")
public class Controller {
    @Autowired
    PersonService personService;

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @PostMapping
    public ResponseEntity<PersonOutputDto> addPerson(@RequestBody PersonInputDto person) {
        try {
            return ResponseEntity.ok(personService.addPerson(person));
        } catch (Exception e) {
            throw new UnprocessableEntityException("Entidad no procesable");
        }
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
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

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @PutMapping("/{id}")
    public ResponseEntity<PersonOutputDto> updatePerson(@RequestBody PersonInputDto person, @PathVariable int id)
            throws Exception {
        return ResponseEntity.ok(personService.updatePerson(person, id));
    }

}

