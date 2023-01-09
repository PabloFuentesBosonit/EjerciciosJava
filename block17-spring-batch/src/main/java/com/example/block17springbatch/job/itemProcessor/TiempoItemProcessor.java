package com.example.block17springbatch.job.itemProcessor;

import com.example.block17springbatch.domain.Tiempo;
import org.springframework.batch.item.ItemProcessor;

public class TiempoItemProcessor implements ItemProcessor<Tiempo, Tiempo> {

    @Override
    public Tiempo process(Tiempo tiempo) throws Exception {
        return tiempo;
    }
}
