package com.example.block13mongodb.application;

import com.example.block13mongodb.domain.Person;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PersonService {
    List<Person> findAll();
    Page<Person> findAllPaginated(int offset, int pageSize);
    Person findById(int id);
    Person update(Person newPerson, int id);
    void delete(int id);
    Person save(Person person);
}
