package com.example.block13mongodb.repository;


import com.example.block13mongodb.domain.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PersonRepository extends MongoRepository<Person, Integer> {
}
