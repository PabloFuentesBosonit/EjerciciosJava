package com.example.block17springbatch.configutarion;

import com.example.block17springbatch.domain.ErrorTemperatura;
import com.example.block17springbatch.domain.Tiempo;
import com.example.block17springbatch.domain.TiempoRiesgo;
import com.example.block17springbatch.listener.*;
import com.example.block17springbatch.steps.secondStep.ErrorItemProcessor;
import com.example.block17springbatch.steps.firstStep.TiempoItemProcessor;
import com.example.block17springbatch.steps.firstStep.TiempoItemWriter;
import com.example.block17springbatch.steps.secondStep.ErrorItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;


    // Lo que lee el csv: EL READER de Step1 y Step2
    @Bean
    public FlatFileItemReader<Tiempo> reader() {
        return new FlatFileItemReaderBuilder<Tiempo>()
                .name("tiempoItemReader")
                .resource(new ClassPathResource("prueba.csv"))
                .linesToSkip(1)
                .delimited()
                .names("localidad","temperatura","fecha")
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Tiempo>() {{
                    setTargetType(Tiempo.class);
                }})
                .build();
    }

    // EL PROCESSOR de Step1

    @Bean
    public ItemProcessor tiempoProcessor() {
        return new TiempoItemProcessor();
    }


    // EL WRITER de Step1

    @Bean
    public ItemWriter tiempoWriter() {
        return new TiempoItemWriter();
    }




    // EL PROCESSOR de Step2
    @Bean
    public ItemProcessor errorProcessor(){
        return new ErrorItemProcessor();
    }

    // EL WRITER de Step2

    @Bean
    public ItemWriter errorWriter() {return new ErrorItemWriter();}


    //Los Listeners

    @Bean
    public TiempoJobExecutionListener jobExecutionListener() {
        return new TiempoJobExecutionListener();
    }

    @Bean
    public TiempoItemReaderListener readerListener() {
        return new TiempoItemReaderListener();
    }

    @Bean
    public TiempoItemProcessListener tiempoItemProcessListener() {
        return new TiempoItemProcessListener();
    }

    @Bean
    public TiempoItemWriterListener writerListener() {
        return new TiempoItemWriterListener();
    }

    @Bean
    public ErrorStepExecutionListener stepExecutionListener() {return new ErrorStepExecutionListener(); }



    // JOB configuration: esto es la automatización de los pasos

    @Bean
    public Job job(TiempoJobExecutionListener jobExecutionListener,Step step, Step step2) {
        Job job = jobBuilderFactory.get("job1")
                .incrementer(new RunIdIncrementer())
                .listener(jobExecutionListener)
                .flow(step)
                .next(step2)
                .end()
                .build();
        return job;
    }

    // Analiza el fichero csv y guarda sus registros en la base de datos
    @Bean
    public Step step(
            TiempoItemWriter writer,
            TiempoItemProcessor processor,
            TiempoItemReaderListener readerListener,
            TiempoItemProcessListener tiempoItemProcessListener,
            TiempoItemWriterListener writerListener) {

        TaskletStep step = stepBuilderFactory.get("step1")
                .<Tiempo, TiempoRiesgo>chunk(100)
                .reader(reader())
                .processor(processor)
                .writer(writer)
                //.listener(readerListener)
                //.listener(tiempoItemProcessListener)
                //.listener(writerListener)
                .build();
        return step;
    }

    //Guarda los errores encontrado en otro fichero csv
    @Bean
    public Step step2(ErrorItemProcessor processor, ErrorItemWriter writer, ErrorStepExecutionListener listener) {

        TaskletStep step = stepBuilderFactory.get("step2")
                .<Tiempo, ErrorTemperatura>chunk(100)
                //.faultTolerant()
                //.skipLimit(40)
                //.skip(Exception.class)
                .reader(reader())
                .processor(processor)
                .writer(writer)
                .listener(listener)
                .build();
        return step;
    }



}
