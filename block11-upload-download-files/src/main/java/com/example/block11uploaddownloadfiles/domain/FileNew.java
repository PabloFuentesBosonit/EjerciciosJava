package com.example.block11uploaddownloadfiles.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class FileNew {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    private String name;

    private Date uploadDate;

    private byte[] content;

}
