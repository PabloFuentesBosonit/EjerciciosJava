package com.example.block13mongodb.application;

import com.example.block13mongodb.domain.Person;
import com.example.block13mongodb.repository.PersonRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;

    @PostConstruct
    public void init() {
        personRepository.deleteAll();
    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public Page<Person> findAllPaginated(int offset, int pageSize) {
        return personRepository.findAll(PageRequest.of(offset, pageSize));
    }

    @Override
    public Person findById(int id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Person with ID " + id + " not found"));
    }

    @Override
    public Person update(Person newPerson, int id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Person with ID " + id + " not found"));

        Boolean isEqual = Objects.equals(person, newPerson);

        if (isEqual) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot update. They are the same persons.");

        return personRepository.save(newPerson);
    }

    @Override
    public void delete(int id) {
        personRepository.deleteById(id);
    }

    @Override
    public Person save(Person person) {
        personRepository.deleteAll();

        return personRepository.save(person);
    }
}
