package com.example.block17springbatch.job.itemWriter;

import com.example.block17springbatch.domain.TiempoRiesgo;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

public class TiempoRiesgoItemWriter implements ItemWriter<TiempoRiesgo> {
    @Override
    public void write(Chunk<? extends TiempoRiesgo> chunk) throws Exception {

    }
}
