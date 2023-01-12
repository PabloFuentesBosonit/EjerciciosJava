package com.example.block17springbatch.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;

public class ErrorStepExecutionListener extends StepExecutionListenerSupport {

    @Override
    public ExitStatus afterStep(StepExecution stepExecution){
        if(stepExecution.getWriteCount() > 100){
            return ExitStatus.FAILED;
        }
        return null;
    }
}
