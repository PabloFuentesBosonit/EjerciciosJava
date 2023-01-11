package com.example.block17springbatch.listener;

import com.example.block17springbatch.domain.Tiempo;
import com.example.block17springbatch.domain.TiempoRiesgo;
import org.springframework.batch.core.ItemProcessListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TiempoItemProcessListener implements ItemProcessListener<Tiempo, TiempoRiesgo> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TiempoItemProcessListener.class);

    @Override
    public void beforeProcess(Tiempo tiempo) {LOGGER.info("beforeProcess");}

    @Override
    public void afterProcess(Tiempo tiempo, TiempoRiesgo tiempoRiesgo) {
        LOGGER.info("afterProcess: " + tiempo + " ---> " + tiempoRiesgo);
    }

    @Override
    public void onProcessError(Tiempo tiempo, Exception e) {
        LOGGER.info("onProcessError");
    }
}
