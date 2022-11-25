package com.example.block7crudvalidation.application;

import com.example.block7crudvalidation.controller.dto.PersonInputDto;
import com.example.block7crudvalidation.controller.dto.PersonMapper;
import com.example.block7crudvalidation.controller.dto.PersonOutputDto;
import com.example.block7crudvalidation.domain.Person;
import com.example.block7crudvalidation.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService{
    @Autowired
    PersonRepository personRepository;

    @Override
    public PersonOutputDto addPerson(PersonInputDto person) {
        Person persona = PersonMapper.Instance.personInputDTOToPerson(person);
        Person personDb = personRepository.save(persona);
        return PersonMapper.Instance.personToPersonOutputDTO(personDb);
    }

    @Override
    public PersonOutputDto getPersonById(int id) {
        Optional<Person> person = personRepository.findById(id);
        return PersonMapper.Instance.personToPersonOutputDTO(person.get());
    }

    @Override
    public void deletePersonById(int id) {
        personRepository.findById(id).orElseThrow();
        personRepository.deleteById(id);
    }
    @Override
    public List<PersonOutputDto> getAllPersons(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return personRepository.findAll()
                .stream()
                .map(person -> PersonMapper.Instance.personToPersonOutputDTO(person)).toList();
    }

    @Override
    public PersonOutputDto updatePerson(PersonInputDto person, int id) throws Exception {
        Optional<Person> personaDb = personRepository.findById(id);
        Person personInput = PersonMapper.Instance.personInputDTOToPerson(person);
        Boolean isEqual = Objects.equals(personaDb, personInput);
        if(isEqual){
            throw new Exception("Son iguales");
        }
        personRepository.save(personInput);
        return PersonMapper.Instance.personToPersonOutputDTO(personInput);
    }
}


