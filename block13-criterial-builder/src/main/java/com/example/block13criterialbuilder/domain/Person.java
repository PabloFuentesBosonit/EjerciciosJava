package com.example.block13criterialbuilder.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_person", nullable = false)
    private int id_person;

    @Column(name = "usuario", nullable = false, length = 10)
    private String usuario;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "personal_email", nullable = false)
    private String company_email;

    @Column(name = "company_email", nullable = false)
    private String personal_email;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @Column(name="created")
    @Temporal(TemporalType.DATE)
    private Date created;

}
