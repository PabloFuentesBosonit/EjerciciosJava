package com.example.block17springbatch.steps.firstStep;

import com.example.block17springbatch.domain.TiempoRiesgo;
import com.example.block17springbatch.repository.TiempoRiesgoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class TiempoItemWriter implements ItemWriter<TiempoRiesgo> {

    @Autowired
    private TiempoRiesgoRepository repository;

    @Override
    public void write(List<? extends TiempoRiesgo> items) {
        log.info("Registros recibidos de tiempo: {}", items.size());
        repository.saveAll(items);
    }
}
