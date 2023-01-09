package com.example.block17springbatch.job.itemProcessor;

import com.example.block17springbatch.domain.ErrorTemperatura;
import com.example.block17springbatch.domain.Tiempo;
import org.springframework.batch.item.ItemProcessor;

public class ErrorTemperaturaItemProcessor implements ItemProcessor<Tiempo, ErrorTemperatura> {

    @Override
    public ErrorTemperatura process(Tiempo tiempo) throws Exception {
        if(tiempo.getTemperatura() > 50 || tiempo.getTemperatura() < -20){
            return new ErrorTemperatura(tiempo.getId(), tiempo.getLocalidad(), tiempo.getTemperatura(), tiempo.getFecha());
        }

        return null;
    }
}
