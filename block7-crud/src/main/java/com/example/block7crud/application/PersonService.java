package com.example.block7crud.application;

import com.example.block7crud.controller.dto.PersonInputDto;
import com.example.block7crud.controller.dto.PersonOutputDto;

public interface PersonService {
    PersonOutputDto addPerson(PersonInputDto person);
    PersonOutputDto getPersonById (int id);
    void deletePersonById(int id);
    Iterable<PersonOutputDto>getAllPerson(int pageNumber, int pageSize);
    PersonOutputDto updatePerson(PersonInputDto person, int id);
}
