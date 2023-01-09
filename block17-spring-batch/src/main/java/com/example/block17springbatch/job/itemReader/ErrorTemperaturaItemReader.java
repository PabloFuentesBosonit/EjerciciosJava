package com.example.block17springbatch.job.itemReader;

import com.example.block17springbatch.domain.ErrorTemperatura;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class ErrorTemperaturaItemReader implements ItemReader<ErrorTemperatura> {


    @Override
    public ErrorTemperatura read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return null;
    }
}
