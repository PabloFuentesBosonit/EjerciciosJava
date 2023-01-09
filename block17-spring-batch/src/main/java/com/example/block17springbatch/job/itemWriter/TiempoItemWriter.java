package com.example.block17springbatch.job.itemWriter;

import com.example.block17springbatch.application.TiempoService;
import com.example.block17springbatch.domain.Tiempo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;

@Slf4j
public class TiempoItemWriter implements ItemWriter<Tiempo> {

    @Autowired
    private TiempoService tiempoService;

    @Override
    public void write(Chunk<? extends Tiempo> chunk) throws Exception {
        log.info("Registros recibido de tiempos : {}", chunk.size());

        chunk.forEach(tiempo -> tiempoService.create(tiempo));
    }
}
