package com.example.block7crud.domain;

import com.example.block7crud.controller.dto.PersonInputDto;
import com.example.block7crud.controller.dto.PersonOutputDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    @Id
    @GeneratedValue
    int id;
    String name;
    int age;
    String town;

    public Person (PersonInputDto personInputDto){
        this.name = personInputDto.getName();
        this.age = personInputDto.getAge();
        this.town = personInputDto.getTown();
    }

    public PersonOutputDto personToPersonOutputDto() {
        return new PersonOutputDto(
                this.id,
                this.name,
                this.age,
                this.town
        );
    }
}
