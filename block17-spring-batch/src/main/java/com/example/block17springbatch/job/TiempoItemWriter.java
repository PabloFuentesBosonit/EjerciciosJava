package com.example.block17springbatch.job;

import com.example.block17springbatch.domain.TiempoRiesgo;
import com.example.block17springbatch.repository.TiempoRiesgoRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TiempoItemWriter implements ItemWriter<TiempoRiesgo> {
    @Autowired
    private TiempoRiesgoRepository repository;

    @Override
    public void write(List<? extends TiempoRiesgo> list) {
        repository.saveAll(list);
    }
}
