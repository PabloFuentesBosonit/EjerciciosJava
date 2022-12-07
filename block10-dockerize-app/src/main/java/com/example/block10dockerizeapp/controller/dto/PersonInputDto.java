package com.example.block10dockerizeapp.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonInputDto {
    int id;
    String name;
    int age;
    String town;
}
