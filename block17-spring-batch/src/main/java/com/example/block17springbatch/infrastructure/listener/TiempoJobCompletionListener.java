package com.example.block17springbatch.infrastructure.listener;

import com.example.block17springbatch.infrastructure.repository.TiempoRiesgoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TiempoJobCompletionListener implements JobExecutionListener {


    @Autowired
    public TiempoRiesgoRepository tiempoRiesgoRepository;

    @Override
    public void beforeJob(JobExecution jobExecution){

    }

    @Override
    public void afterJob(JobExecution jobExecution){
        if(jobExecution.getStatus() == BatchStatus.COMPLETED){
            log.info("TIEMPO JOB FINISHED");
        } else if (jobExecution.getStatus() == BatchStatus.FAILED){
            log.error("DELETE tiempo_riesgo table");
            tiempoRiesgoRepository.deleteAll();
        }
    }
}
