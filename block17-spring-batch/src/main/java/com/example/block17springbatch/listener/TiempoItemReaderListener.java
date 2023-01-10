package com.example.block17springbatch.listener;

import com.example.block17springbatch.domain.Tiempo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemReadListener;


public class TiempoItemReaderListener implements ItemReadListener<Tiempo> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TiempoJobExecutionListener.class);

    @Override
    public void beforeRead()  {
        LOGGER.info("beforeRead");
    }

    @Override
    public void afterRead(Tiempo tiempo) {
        LOGGER.info("afterRead: " + tiempo.toString());
    }

    @Override
    public void onReadError(Exception e){
        LOGGER.info("onReadError");
    }
}
