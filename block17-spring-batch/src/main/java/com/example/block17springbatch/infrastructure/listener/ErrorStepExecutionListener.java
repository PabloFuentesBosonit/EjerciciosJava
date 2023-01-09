package com.example.block17springbatch.infrastructure.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ErrorStepExecutionListener implements StepExecutionListener {

    @Override
    public ExitStatus afterStep(StepExecution stepExecution){
        //Si el programa encuentra mas de 100 errores, para la apliacion
        if(stepExecution.getWriteCount() > 10){
            return ExitStatus.FAILED;
        }
        return null;
    }
}
