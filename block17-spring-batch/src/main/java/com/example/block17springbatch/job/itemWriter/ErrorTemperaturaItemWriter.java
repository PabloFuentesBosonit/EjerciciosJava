package com.example.block17springbatch.job.itemWriter;

import com.example.block17springbatch.application.ErrorTemperaturaService;
import com.example.block17springbatch.domain.ErrorTemperatura;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;

@Slf4j
public class ErrorTemperaturaItemWriter implements ItemWriter<ErrorTemperatura> {

    @Autowired
    public ErrorTemperaturaService errorTemperaturaService;

    @Override
    public void write(Chunk<? extends ErrorTemperatura> chunk) throws Exception {
        log.info("Registros recividos de errores: {}", chunk.size());

        chunk.forEach(tiempo -> errorTemperaturaService.create(tiempo));
    }
}
