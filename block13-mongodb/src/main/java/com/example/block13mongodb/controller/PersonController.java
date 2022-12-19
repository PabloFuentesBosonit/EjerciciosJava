package com.example.block13mongodb.controller;

import com.example.block13mongodb.application.PersonService;
import com.example.block13mongodb.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    PersonService personService;

    @GetMapping("/all")
    List<Person> getAll() {
        return personService.findAll();
    }

    @GetMapping
    List<Person> getByPage(@RequestParam("offset") int offset, @RequestParam("pageSize") int pageSize) {
        return personService.findAllPaginated(offset, pageSize).getContent();
    }

    @GetMapping("/{id}")
    Person getById(@PathVariable int id) {
        return personService.findById(id);
    }

    @PostMapping
    Person create(@RequestBody Person newPerson) {
        return personService.save(newPerson);
    }

    @PutMapping("/{id}")
    Person putById(@RequestBody Person newPerson, @PathVariable int id) {
        return personService.update(newPerson, id);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable int id) {
        personService.delete(id);
    }
}
