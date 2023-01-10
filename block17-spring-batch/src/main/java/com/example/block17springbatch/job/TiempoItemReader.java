package com.example.block17springbatch.job;

import com.example.block17springbatch.domain.Tiempo;
import com.example.block17springbatch.repository.TiempoRepository;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

import java.util.Iterator;

public class TiempoItemReader implements ItemReader<Tiempo> {
    @Autowired
    private TiempoRepository tiempoRepository;

    private Iterator<Tiempo> userIterator;

    @Override
    public Tiempo read(){
        return new Tiempo();
    }
}
