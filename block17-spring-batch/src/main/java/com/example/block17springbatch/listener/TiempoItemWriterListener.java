package com.example.block17springbatch.listener;
import com.example.block17springbatch.domain.TiempoRiesgo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemWriteListener;

import java.util.List;

public class TiempoItemWriterListener implements ItemWriteListener<TiempoRiesgo>  {

    private static final Logger LOGGER = LoggerFactory.getLogger(TiempoItemWriterListener.class);

    @Override
    public void beforeWrite(List<? extends TiempoRiesgo> list) {
        LOGGER.info("beforeWrite");
    }

    @Override
    public void afterWrite(List<? extends TiempoRiesgo> list) {
        for (TiempoRiesgo tiempoRiesgo : list) {
            LOGGER.info("afterWrite :" + tiempoRiesgo.toString());
        }
    }

    @Override
    public void onWriteError(Exception e, List<? extends TiempoRiesgo> list) {
        LOGGER.info("onWriteError");
    }
}
