package com.example.block17springbatch.steps.secondStep;

import com.example.block17springbatch.domain.ErrorTemperatura;
import com.example.block17springbatch.domain.Tiempo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class ErrorItemProcessor implements ItemProcessor<Tiempo, ErrorTemperatura> {

    @Override
    public ErrorTemperatura process(Tiempo tiempo) {
        if(tiempo.getTemperatura() > 50 || tiempo.getTemperatura() < -20){
            log.info("Registros recibido de error: {}", tiempo);
            return new ErrorTemperatura(tiempo.getLocalidad(), tiempo.getFecha(), tiempo.getTemperatura());
        }
        return null;
    }

}
