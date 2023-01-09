package com.example.block17springbatch;

import com.example.block17springbatch.domain.ErrorTemperatura;
import com.example.block17springbatch.domain.Tiempo;
import com.example.block17springbatch.domain.TiempoRiesgo;
import com.example.block17springbatch.infrastructure.listener.ErrorStepExecutionListener;
import com.example.block17springbatch.infrastructure.listener.TiempoJobCompletionListener;
import com.example.block17springbatch.job.itemProcessor.ErrorCsvItemProcessor;
import com.example.block17springbatch.job.itemProcessor.ErrorTemperaturaItemProcessor;
import com.example.block17springbatch.job.itemProcessor.TiempoItemProcessor;
import com.example.block17springbatch.job.itemProcessor.TiempoRiesgoItemProcessor;
import com.example.block17springbatch.job.itemReader.ErrorCsvItemReader;
import com.example.block17springbatch.job.itemReader.ErrorTemperaturaItemReader;
import com.example.block17springbatch.job.itemReader.TiempoItemReader;
import com.example.block17springbatch.job.itemWriter.ErrorTemperaturaItemWriter;
import com.example.block17springbatch.job.itemWriter.TiempoItemWriter;
import com.example.block17springbatch.job.itemWriter.TiempoRiesgoItemWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.builder.StepBuilder;
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
import org.springframework.core.task.SimpleAsyncTaskExecutor;

import java.io.IOException;
import java.io.Writer;

@EnableBatchProcessing
@Configuration
@Slf4j
public class BatchConfig {
    @Autowired
    public JobBuilder jobBuilder;
    @Autowired
    public StepBuilder stepBuilder;

    //READER
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

    @Bean
    public ErrorTemperaturaItemReader errorItemReader(){return new ErrorTemperaturaItemReader();}

    @Bean
    public ErrorCsvItemReader errorCsvItemsReader() {return new ErrorCsvItemReader();}

    @Bean
    public TiempoItemReader tiempoRiesgoReader() {return new TiempoItemReader();}

    //PROCESADOR

    @Bean
    public TiempoItemProcessor tiempoItemProcessor() {return new TiempoItemProcessor(); }

    @Bean
    public ErrorTemperaturaItemProcessor errorTemperaturaItemProcessor() {return new ErrorTemperaturaItemProcessor(); }

    @Bean
    public ErrorCsvItemProcessor errorCsvItemProcessor() {return new ErrorCsvItemProcessor();}

    @Bean
    public TiempoRiesgoItemProcessor tiempoRiesgoItemProcessor() {return new TiempoRiesgoItemProcessor(); }


    //WRITER

    @Bean
    public FlatFileItemWriter<ErrorTemperatura> errorCsvWriter() {
        FlatFileItemWriter<ErrorTemperatura> writer = new FlatFileItemWriter<ErrorTemperatura>();

        writer.setResource(new FileSystemResource("src/main/resources/errorsOutput.csv"));

        writer.setResource(new FileSystemResource("springbatch/src/main/resources/errorsOutput.csv"));

        writer.setHeaderCallback(new FlatFileHeaderCallback() {

            @Override
            public void writeHeader(Writer writer) throws IOException {
                writer.write("ID, IDTiempo, Localidad, Temperatura, Fecha");
            }
        });

        writer.setLineAggregator(new DelimitedLineAggregator<ErrorTemperatura>() {
            {
                setDelimiter(",");
                setFieldExtractor(new BeanWrapperFieldExtractor<ErrorTemperatura>(){
                    {
                        setNames(new String[]{"id","idTiempo", "localidad", "temperatura", "fecha"});
                    }});
            }});

        return writer;
    }

    @Bean
    public ItemWriter<Tiempo> tiempoItemWriter(){return new TiempoItemWriter(); }

    @Bean
    public ItemWriter<TiempoRiesgo> tiempoRiesgoItemWriter(){return new TiempoRiesgoItemWriter(); }

    @Bean
    public ErrorTemperaturaItemWriter errorWriter(){return new ErrorTemperaturaItemWriter();}




    //JOB CONFIGUTARION
    @Bean
    public Job job1(TiempoJobCompletionListener listener, Step step1, Step step2, Step step3, Step step4){

        Flow firstFlow = new FlowBuilder<Flow>("firstFlow")
                .start(step1)
                .build();

        Flow secondFlow = new FlowBuilder<Flow>("secondFlow")
                .start(step2)
                .build();

        Flow parallelFlow = new FlowBuilder<Flow>("parallelFlow")
                .start(step3)
                .split(new SimpleAsyncTaskExecutor())
                .add(secondFlow)
                .build();

        Flow errorsFlow = new FlowBuilder<Flow>("errorsFlow")
                .start(step4)
                .build();

        return this.jobBuilder.start(job1)
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .start(firstFlow)
                .next(parallelFlow)
                .next(errorsFlow)
                .end()
                .build();
    }

    //STEPS
    //Analiza el fichero csv y guarda sus registros en la base de datos
    @Bean
    public Step step1() {
        return stepBuilder.partitioner("step1")
                .chunk(10)
                .reader(tiempoReader())
                .processor(tiempoProcessor())
                .writer(tiempoItemWriter())
                .build();
    }


    //Calcula en riesgo de cada fecha y localidad
    @Bean
    public Step step3() {
        return stepBuilder.get("step3")
                .chunk(10)
                .reader(tiempoRiesgoReader())
                .processor(tiempoRiesgoItemProcessor())
                .writer(tiempoRiesgoItemWriter())
                .build();
    }

    //Comprueba errores en los registros y los separa en la base de datos
    @Bean
    public Step step2(ErrorStepExecutionListener listener) {
        return stepBuilder.get("step2")
                .chunk(10)
                .reader(errorItemReader())
                .processor(errorProcessor())
                .writer(errorWriter())
                .listener(listener)
                .build();
    }

    //Guarda los errores encontrados en otro fichero csv
    @Bean
    public Step step() {
        return stepBuilder.get("step4")
                .chunk(10)
                .reader(errorCsvItemsReader())
                .processor(errorCsvItemProcessor())
                .writer(errorCsvWriter())
                .build();
    }

}
