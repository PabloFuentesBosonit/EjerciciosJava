package com.example.block7crudvalidation.profesor.domain;

import com.example.block7crudvalidation.person.domain.Person;
import com.example.block7crudvalidation.student.domain.Student;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter

@Entity
@Table(name = "Profesor")
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id_profesor;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_person", nullable = false)
    Person person;

    @OneToMany(mappedBy = "profesor",cascade = CascadeType.ALL)
    @ToString.Exclude
    List<Student> studentsProfesor = new ArrayList<>();

    @Column(name = "comments")
    String comments;
    @Column(name = "brand")
    String brand;


}

