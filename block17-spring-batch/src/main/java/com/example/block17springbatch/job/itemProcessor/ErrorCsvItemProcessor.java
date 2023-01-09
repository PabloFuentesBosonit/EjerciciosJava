package com.example.block17springbatch.job.itemProcessor;

import com.example.block17springbatch.domain.ErrorTemperatura;
import org.springframework.batch.item.ItemProcessor;

public class ErrorCsvItemProcessor implements ItemProcessor<ErrorTemperatura, ErrorTemperatura> {

    @Override
    public ErrorTemperatura process(ErrorTemperatura errorTemperatura) throws Exception {
        return errorTemperatura;
    }
}
