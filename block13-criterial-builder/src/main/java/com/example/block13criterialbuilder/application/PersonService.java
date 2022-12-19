package com.example.block13criterialbuilder.application;

import com.example.block13criterialbuilder.controller.dto.PersonInputDto;
import com.example.block13criterialbuilder.controller.dto.PersonOutputDto;
import org.springframework.data.domain.Page;
import java.util.HashMap;
import java.util.List;

public interface PersonService {
    PersonOutputDto addPerson(PersonInputDto person);
    PersonOutputDto getPersonById(int id);
    void deletePersonById(int id);
    Iterable<PersonOutputDto> getAllPersons (int pageNumber, int pageSize);
    PersonOutputDto updatePerson(PersonInputDto person, int id) throws Exception;
    PersonOutputDto getPersonByName(String name);

    Page<PersonOutputDto> allPersonByPage(int page, int pageSize);
    List<PersonOutputDto> getCondition(HashMap<String, Object> data);
}
