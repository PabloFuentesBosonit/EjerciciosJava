package com.example.block7crudvalidation.student.domain;

import com.example.block7crudvalidation.person.domain.Person;
import com.example.block7crudvalidation.profesor.domain.Profesor;
import com.example.block7crudvalidation.subject.domain.Subject;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

@Entity
@Table(name = "Estudiante")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_student", nullable = false)
    private int id_student;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "id_person", nullable = false, unique = true)
    private Person person;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profesor")
    Profesor profesor;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "subject")
    @ToString.Exclude
    List<Subject> subjectStudent = new ArrayList<>();

    @Column(name = "numero_horas")
    private int numHours;

    @Column(name = "comentarios")
    private String comments;

    @Column(name = "rama")
    private String branch;

}
