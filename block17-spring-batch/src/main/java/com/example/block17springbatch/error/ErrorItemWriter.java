package com.example.block17springbatch.error;

import com.example.block17springbatch.domain.ErrorTemperatura;
import com.example.block17springbatch.repository.TiempoRiesgoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@Slf4j
public class ErrorItemWriter implements ItemWriter<ErrorTemperatura> {


    @Autowired
    private TiempoRiesgoRepository tiempoRiesgoRepository;

    @Override
    public void write(List<? extends ErrorTemperatura> items) throws Exception {
        log.info("Registros recibido de errores: {}", items.size());
        // borrar de la base de datos los erroneos
    }
}
