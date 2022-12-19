package com.example.block13criterialbuilder.controller;

import com.example.block13criterialbuilder.application.PersonService;
import com.example.block13criterialbuilder.controller.dto.PersonInputDto;
import com.example.block13criterialbuilder.controller.dto.PersonOutputDto;
import com.example.block13criterialbuilder.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/person")
public class PersonController {
    @Autowired
    PersonService personService;

    @Autowired
    PersonRepository personRepository;

    @PostMapping("/addperson")
    public ResponseEntity<PersonOutputDto> addPerson(@RequestBody PersonInputDto person) throws Exception {
        try {
            return ResponseEntity.ok(personService.addPerson(person));
        } catch (Exception e) {
            System.out.println(person.toString());
            throw new Exception();
        }
    }


    @GetMapping("/person/{id}")
    public ResponseEntity<PersonOutputDto> getPersonById(@PathVariable int id) throws Exception {
        try{
            return ResponseEntity.ok().body(personService.getPersonById(id));
        }catch (Exception e){
            throw new Exception("Usuario no encontrado");
        }

    }

    @GetMapping("/user/{name}")
    public ResponseEntity<PersonOutputDto> getPersonByName(@PathVariable String name) throws Exception {
        try {
            return ResponseEntity.ok().body(personService.getPersonByName(name));
        } catch (Exception e) {
            throw new Exception("Los campos no cumplen los requisitos");
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

    @GetMapping("/getall")
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

    @GetMapping("/page")
    public Page<PersonOutputDto> allPersonByPage(@RequestParam int page, @RequestParam int pageSize){
        return personService.allPersonByPage(page, pageSize);
    }

    public static final String GREATER_THAN="greater";
    public static final String LESS_THAN="less";
    public static final String EQUAL="equal";

    @GetMapping("/filter")
    public List<PersonOutputDto> getData(@RequestParam(required=false) String usuario,
                                         @RequestParam(required=false) String name,
                                         @RequestParam(required=false) String surname,
                                         @RequestParam(required=false) @DateTimeFormat(pattern="dd-MM-yyyy") Date createdDate,
                                         @RequestParam(required=false) String dateCondition) {

        HashMap<String, Object> data = new HashMap<>();

        if (usuario!=null) data.put("usuario", usuario);
        if (name!=null) data.put("name",name);
        if (surname!=null) data.put("surname",surname);
        if (dateCondition==null) dateCondition=GREATER_THAN;
        if (!dateCondition.equals(GREATER_THAN) && !dateCondition.equals(LESS_THAN) && !dateCondition.equals(EQUAL))
            dateCondition=GREATER_THAN;
        if (createdDate!=null) {
            data.put("created",createdDate);
            data.put("dateCondition",dateCondition);
        }

        return personService.getCondition(data);

    }

}


