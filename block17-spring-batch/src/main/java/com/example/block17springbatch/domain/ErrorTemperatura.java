package com.example.block17springbatch.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorTemperatura {
    int id;
    String localidad;
    int temperatura;
    String fecha;


}
