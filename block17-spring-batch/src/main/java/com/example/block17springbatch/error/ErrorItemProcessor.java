package com.example.block17springbatch.error;

import com.example.block17springbatch.domain.ErrorTemperatura;
import com.example.block17springbatch.domain.Tiempo;
import org.springframework.batch.item.ItemProcessor;

public class ErrorItemProcessor implements ItemProcessor<Tiempo, ErrorTemperatura> {

    @Override
    public ErrorTemperatura process(Tiempo tiempo) {
        if(tiempo.getTemperatura() > 50 || tiempo.getTemperatura() < -20){
            return new ErrorTemperatura(tiempo.getLocalidad(), tiempo.getFecha(), tiempo.getTemperatura());
        }
        return null;
    }
}
