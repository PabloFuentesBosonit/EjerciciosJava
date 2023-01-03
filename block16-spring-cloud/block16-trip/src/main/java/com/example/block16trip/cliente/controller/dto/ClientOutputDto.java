package com.example.block16trip.cliente.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class ClientOutputDto {
    int id;
    String name;
    String surname;
    int age;
    String email;
    String phone;
}
