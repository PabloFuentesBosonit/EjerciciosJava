package com.example.block17springbatch.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorTemperatura {
    private int id;
    private String localidad;
    private String fecha;
    private int temperatura;

}