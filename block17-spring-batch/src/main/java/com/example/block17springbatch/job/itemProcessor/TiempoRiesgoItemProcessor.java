package com.example.block17springbatch.job.itemProcessor;

import com.example.block17springbatch.domain.TiempoRiesgo;
import com.example.block17springbatch.infrastructure.dto.TiempoRiesgoDto;
import org.springframework.batch.item.ItemProcessor;

public class TiempoRiesgoItemProcessor implements ItemProcessor<TiempoRiesgoDto, TiempoRiesgo> {

    @Override
    public TiempoRiesgo process(TiempoRiesgoDto item) throws Exception {
        TiempoRiesgo tiempoRiesgo = new TiempoRiesgo();
        tiempoRiesgo.setLocalidad(item.getLocalidad());
        tiempoRiesgo.setAnio(item.getAnio());
        tiempoRiesgo.setMes(item.getMes());
        Float media = item.getTemperaturaMedia();
        tiempoRiesgo.setTemperaturaMedia(media);
        tiempoRiesgo.setNumeroMediciones(item.getNumeroMediciones());
        int riesgo = 0;
        //Riesgo
        if(media >= 36){
            riesgo = TiempoRiesgo.HIGH;
        } else if(media < 36 && media >= 32){
            riesgo = TiempoRiesgo.NORMAL;
        } else if (media < 32) {
            riesgo = TiempoRiesgo.LOW;
        }
        tiempoRiesgo.setRiesgo(riesgo);

        //log.info("generado" + tiempoRiesgo.toString());

        return tiempoRiesgo;
    }

}
