package com.example.block17springbatch.job;

import com.example.block17springbatch.domain.Tiempo;
import org.springframework.batch.item.ItemReader;


public class TiempoItemReader implements ItemReader<Tiempo> {
    @Override
    public Tiempo read(){
        return new Tiempo();
    }
}
