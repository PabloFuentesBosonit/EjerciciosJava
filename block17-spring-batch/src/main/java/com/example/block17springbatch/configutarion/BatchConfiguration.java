package com.example.block17springbatch.configutarion;

import com.example.block17springbatch.domain.ErrorTemperatura;
import com.example.block17springbatch.domain.Tiempo;
import com.example.block17springbatch.domain.TiempoRiesgo;
import com.example.block17springbatch.error.ErrorItemProcessor;
import com.example.block17springbatch.job.TiempoItemProcessor;
import com.example.block17springbatch.job.TiempoItemWriter;
import com.example.block17springbatch.listener.TiempoItemProcessListener;
import com.example.block17springbatch.listener.TiempoItemReaderListener;
import com.example.block17springbatch.listener.TiempoItemWriterListener;
import com.example.block17springbatch.listener.TiempoJobExecutionListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

import java.io.IOException;
import java.io.Writer;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    // Lo que lee el csv EL READER de tiempo
    @Bean
    public FlatFileItemReader<Tiempo> tiempoReader() {
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

    // EL PROCESSOR de tiempo

    @Bean
    public ItemProcessor tiempoProcessor() {
        return new TiempoItemProcessor();
    }


    // EL WRITER de tiempo

    @Bean
    public ItemWriter tiempoWriter() {
        return new TiempoItemWriter();
    }


    // El processor de Error
    @Bean
    public ItemProcessor errorProcessor(){
        return new ErrorItemProcessor();
    }

    // El writer de error

    @Bean
    public FlatFileItemWriter<ErrorTemperatura> errorWriter() {
        FlatFileItemWriter<ErrorTemperatura> writer = new FlatFileItemWriter<ErrorTemperatura>();

        writer.setResource(new FileSystemResource("src/main/resources/errors.csv"));

        writer.setResource(new FileSystemResource("block17-spring-batch/src/main/resources/errors.csv"));

        writer.setHeaderCallback(new FlatFileHeaderCallback() {
            @Override
            public void writeHeader(Writer writer) throws IOException {
                writer.write("id, Localidad, Temperatura, Fecha");
            }
        });
        writer.setLineAggregator(new DelimitedLineAggregator<ErrorTemperatura>() {{
                setDelimiter(",");
                setFieldExtractor(new BeanWrapperFieldExtractor<ErrorTemperatura>(){{
                        setNames(new String[]{"id", "localidad", "temperatura","fecha"});
                }});
            }});
        return writer;
    }


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


    // JOB configuration esto es la automatización de los pasos

    @Bean
    public Job job(TiempoJobExecutionListener jobExecutionListener,Step step, Step step2) {
        Job job = jobBuilderFactory.get("job1")
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
                .reader(tiempoReader())
                .processor(processor)
                .writer(writer)
                .listener(readerListener)
                .listener(tiempoItemProcessListener)
                .listener(writerListener)
                .build();
        return step;
    }

    //Guarda los errores encontrado en otro fichero csv
    @Bean
    public Step step2(ErrorItemProcessor processor) {

        TaskletStep step = stepBuilderFactory.get("step2")
                .<Tiempo, ErrorTemperatura>chunk(100)
                .reader(tiempoReader())
                .processor(processor)
                .writer(errorWriter())
                .build();
        return step;
    }
}
