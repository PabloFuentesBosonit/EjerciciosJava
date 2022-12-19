package com.example.block13criterialbuilder;

import com.example.block13criterialbuilder.domain.Person;
import com.example.block13criterialbuilder.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.sql.Date;
import java.time.LocalDate;

@SpringBootApplication
public class Block13CriterialBuilderApplication {

	public static void main(String[] args) {
		SpringApplication.run(Block13CriterialBuilderApplication.class, args);
	}

	@Autowired
	PersonRepository personRepository;

	@PostConstruct
	public void meterPeople() {
		personRepository.save(new Person(1,"Persona_1","password","Pablo","Fuentes",
				"napoleon","napo", "Granada",true, Date.valueOf("2022-05-27")));
		personRepository.save(new Person(2,"Persona_2","password","Nacho","Fuentes",
				"napoleon","napo", "Granada",true, Date.valueOf("2021-05-27")));
		personRepository.save(new Person(3,"Persona_3","password","Antonio","Fuentes",
				"napoleon","napo", "Granada",true, Date.valueOf("2020-05-27")));
		personRepository.save(new Person(4,"Persona_4","password","Isabel","Fuentes",
				"napoleon","napo", "Granada",true, Date.valueOf("2019-05-27")));
	}

}
