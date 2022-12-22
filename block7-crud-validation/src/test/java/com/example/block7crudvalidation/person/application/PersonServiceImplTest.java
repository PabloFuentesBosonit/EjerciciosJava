package com.example.block7crudvalidation.person.application;

import com.example.block7crudvalidation.person.controller.dto.PersonInputDto;
import com.example.block7crudvalidation.person.controller.dto.PersonMapper;
import com.example.block7crudvalidation.person.controller.dto.PersonOutputDto;
import com.example.block7crudvalidation.person.domain.Person;
import com.example.block7crudvalidation.person.repository.PersonRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PersonServiceImplTest {

    @Mock
    private PersonRepository repository;

    @InjectMocks
    private PersonService test = new PersonServiceImpl();


    private Person person = new Person();

    private final ArrayList<Person> personList = new ArrayList<>();

    private PersonOutputDto personOutputDto = new PersonOutputDto();

    private PersonInputDto personInputDto = new PersonInputDto();

    @BeforeEach
    void setUp() throws MalformedURLException {
        MockitoAnnotations.openMocks(this);

        person.setId_person(1);
        person.setUsuario("PabloCrack");
        person.setPassword("password");
        person.setName("Pablo");
        person.setSurname("Fuentes");
        person.setCompany_email("pablo@test.com");
        person.setPersonal_email("pablo@bosonit.com");
        person.setCity("Granada");
        person.setActive(true);
        person.setImage_url(new URL("http:/localhost:8080/laVida"));

        personInputDto.setUsuario("PabloCrack");
        personInputDto.setPassword("password");
        personInputDto.setName("Pablo");
        personInputDto.setSurname("Fuentes");
        personInputDto.setCompany_email("pablo@test.com");
        personInputDto.setPersonal_email("pablo@bosonit.com");
        personInputDto.setCity("Granada");
        personInputDto.setActive(true);
        personInputDto.setImage_url(new URL("http:/localhost:8080/laVida"));

        repository.save(person);
        personList.add(person);

        // person = PersonMapper.Instance.personInputDTOToPerson(personInputDto);

    }


    @Test
    void addPerson() {
        when(repository.save(person)).thenReturn(person);
        test.addPerson(personInputDto);
        verify(repository, atLeastOnce())
                .save(person);

    }

    @Test
    void getPersonById() {
        when(repository.findById(person.getId_person()))
                .thenReturn(Optional.ofNullable(person));
        test.getPersonById(person.getId_person());
        //assertThat(person).isIn(repository.findById(person.getId_person()));
    }

    @Test
    void getPersonByName() {
        when(repository.findByName(person.getName()))
                .thenReturn(Optional.ofNullable(person));
        test.getPersonByName(person.getName());

    }

    @Test
    void deletePersonById() {
        when(repository.findById(person.getId_person()))
                .thenReturn(Optional.ofNullable(person));
        test.deletePersonById(person.getId_person());
        assertThat(person).isNotIn(repository.findById(person.getId_person()));
    }


    @Test
    void getAllPersons() {
        int a = 1;
        int b = 1;

        when(repository.findAll()).thenReturn(personList);
        test.getAllPersons(a,b);
    }


    @Test
    void updatePerson() throws Exception {
        when(repository.findById(person.getId_person()))
                .thenReturn(Optional.ofNullable(person));

        assertNotNull(repository.findById(person.getId_person()));

        PersonInputDto newPerson = new PersonInputDto();

        newPerson.setUsuario("PabloCrack");
        newPerson.setPassword("password");
        newPerson.setName("Pablo");
        newPerson.setSurname("Fuentes");
        newPerson.setCompany_email("pablo@test.com");
        newPerson.setPersonal_email("pablo@bosonit.com");
        newPerson.setCity("Granada");
        newPerson.setActive(true);
        newPerson.setImage_url(person.getImage_url());

        assertInstanceOf(PersonInputDto.class, newPerson);

        test.updatePerson(newPerson, person.getId_person());

        verify(repository, atLeastOnce())
                .save(person);
    }
}