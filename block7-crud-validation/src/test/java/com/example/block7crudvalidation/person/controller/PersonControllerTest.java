package com.example.block7crudvalidation.person.controller;

import com.example.block7crudvalidation.person.controller.dto.PersonInputDto;
import com.example.block7crudvalidation.person.controller.dto.PersonMapper;
import com.example.block7crudvalidation.person.domain.Person;
import com.example.block7crudvalidation.person.repository.PersonRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PersonControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private PersonRepository repository;

    private Person person = new Person();
    private PersonInputDto personInputDto = new PersonInputDto();

    @BeforeEach
    void setUp() throws MalformedURLException {
        personInputDto.setUsuario("PabloCrack");
        personInputDto.setPassword("password");
        personInputDto.setName("Pablo");
        personInputDto.setSurname("Fuentes");
        personInputDto.setCompany_email("pablo@bosonit.com");
        personInputDto.setPersonal_email("pablo@test.com");
        personInputDto.setCity("Granada");
        personInputDto.setImage_url(new URL("https://github.com"));
        personInputDto.setActive(true);

        person = PersonMapper.Instance.personInputDTOToPerson(personInputDto);
    }

    @AfterEach
    void tearDown(){
        repository.deleteAll();
    }

    @Test
    void addPerson() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                .post("/addperson")
                .content(mapper.writeValueAsString(personInputDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void getPersonById() throws Exception {
        int id = repository.save(person).getId_person();


        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/person/" + id)
                .accept(MediaType.APPLICATION_JSON);


        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    void getPersonByName() throws Exception {
        String name = repository.save(person).getName();


        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/user/" + name)
                .accept(MediaType.APPLICATION_JSON);


        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

    }

    @Test
    void deletePersonById() throws Exception {
        int id = repository.save(person).getId_person();

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/deleted/" + id)
                .accept(MediaType.APPLICATION_JSON);


        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

    }

    @Test
    void getAllPersons() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/getall")
                .accept(MediaType.APPLICATION_JSON);


        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void updatePerson() throws Exception {

        Person person = repository.save(PersonMapper.Instance.personInputDTOToPerson(personInputDto));
        personInputDto.setName("PRD");

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/modificate/" + person.getId_person())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(personInputDto));

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

    }
}