package com.example.block7crudvalidation.person.application;

import com.example.block7crudvalidation.person.controller.dto.PersonInputDto;
import com.example.block7crudvalidation.person.controller.dto.PersonOutputDto;


public interface PersonService {
    PersonOutputDto addPerson(PersonInputDto person);
    PersonOutputDto getPersonById(int id);
    void deletePersonById(int id);
    Iterable<PersonOutputDto> getAllPersons (int pageNumber, int pageSize);
    PersonOutputDto updatePerson(PersonInputDto person, int id) throws Exception;
    PersonOutputDto getPersonByName(String name);
}
