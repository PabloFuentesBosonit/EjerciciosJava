package com.example.block10dockerizeapp.repository;

import com.example.block10dockerizeapp.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    //Aqui tambien podemos meter las Query
}
