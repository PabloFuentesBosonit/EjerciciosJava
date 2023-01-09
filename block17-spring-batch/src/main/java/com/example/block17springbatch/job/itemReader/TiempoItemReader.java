package com.example.block17springbatch.job.itemReader;

import com.example.block17springbatch.infrastructure.repository.TiempoRepository;
import com.example.block17springbatch.domain.Tiempo;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;

public class TiempoItemReader implements ItemReader<Tiempo> {

    @Autowired
    private TiempoRepository tiempoRepository;

    private Iterator<Tiempo> tiempoIterator;

    @BeforeStep
    public void before(StepExecution stepExecution){
        tiempoIterator = tiempoRepository.findAll().iterator();
    }

    @Override
    public Tiempo read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (tiempoIterator != null && tiempoIterator.hasNext()) {
            return tiempoIterator.next();
        } else {
            return null;
        }
    }
}
