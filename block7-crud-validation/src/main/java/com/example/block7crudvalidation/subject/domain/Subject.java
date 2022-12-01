package com.example.block7crudvalidation.subject.domain;

import com.example.block7crudvalidation.student.domain.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Asignaturas")
public class Subject {
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        @Column(name = "id_subject", nullable = false)
        private int id_subject;

        @ManyToMany
        @JoinTable(name = "studentSubject")
        @ToString.Exclude
        List<Student> studentsSubject = new ArrayList<>();

        @Column(name = "subjectName")
        String subjectName;
        @Column(name = "comentarios")
        String comment;
        @Column(name = "initial_date")
        Date initial_date = new Date();
        @Column(name = "finish_date")
        Date finish_date = new Date();

}
