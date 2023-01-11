package com.example.block17springbatch.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
public class ErrorTemperatura {
    private String localidad;
    private String fecha;
    private int temperatura;
}
