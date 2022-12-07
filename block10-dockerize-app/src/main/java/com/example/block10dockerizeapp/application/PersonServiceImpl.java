package com.example.block10dockerizeapp.application;

import com.example.block10dockerizeapp.controller.dto.PersonInputDto;
import com.example.block10dockerizeapp.controller.dto.PersonOutputDto;
import com.example.block10dockerizeapp.domain.Person;
import com.example.block10dockerizeapp.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;

    List<Person> people = new ArrayList<>();

    @Override
    public PersonOutputDto addPerson(PersonInputDto person) {

        return personRepository.save(new Person(person))
                .personToPersonOutputDto();
    }

    @Override
    public PersonOutputDto getPersonById(int id) {
        return personRepository.findById(id).orElseThrow()
                .personToPersonOutputDto();
    }

    @Override
    public void deletePersonById(int id) {
        personRepository.findById(id);
        personRepository.deleteById(id);
    }

    @Override
    public Iterable<PersonOutputDto> getAllPerson(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return personRepository.findAll(pageRequest).getContent()
                .stream()
                .map(Person::personToPersonOutputDto).toList();
    }

    @Override
    public PersonOutputDto updatePerson(PersonInputDto person) {
        personRepository.findById(person.getId()).orElseThrow();
        return personRepository.save(new Person(person))
                .personToPersonOutputDto();
    }
}
