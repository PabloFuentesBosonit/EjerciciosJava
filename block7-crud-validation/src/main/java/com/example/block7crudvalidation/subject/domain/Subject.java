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
@Table(name = "subjects")
public class Subject {
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        @Column(name = "id_subject", nullable = false)
        private int id_subject;
        // @ManyToOne(fetch = FetchType.LAZY)
       // @JoinColumn(name = "id_profesor")
       // Profesor profesor;
       // @ManyToOne(cascade = CascadeType.ALL)
       // @JoinColumn(name = "id_student")
       // Student student;
        @ManyToMany
        @JoinTable(name = "student_subject")
        @ToString.Exclude
        List<Student> studentsSubject = new ArrayList<>();

        @Column(name = "subject")
        String subjectName;
        @Column(name = "comentarios")
        String comment;
        @Column(name = "initial_date")
        Date initial_date = new Date();
        @Column(name = "finish_date")
        Date finish_date = new Date();

}
