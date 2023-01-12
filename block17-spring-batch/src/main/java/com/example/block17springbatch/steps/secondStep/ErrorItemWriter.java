package com.example.block17springbatch.steps.secondStep;

import com.example.block17springbatch.domain.ErrorTemperatura;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.FileSystemResource;

import java.io.IOException;
import java.io.Writer;

public class ErrorItemWriter extends FlatFileItemWriter<ErrorTemperatura> {

    public ErrorItemWriter() {
        //setResource(new FileSystemResource("src/main/resources/errors.csv"));
        setResource(new FileSystemResource("block17-spring-batch/src/main/resources/errors.csv"));
        setAppendAllowed(false);
        setHeaderCallback(new FlatFileHeaderCallback() {
            @Override
            public void writeHeader(Writer writer) throws IOException {
                writer.write("Localidad, Fecha, Temperatura");
            }
        });
        setLineAggregator(new DelimitedLineAggregator<ErrorTemperatura>(){{
                setDelimiter(",");
                setFieldExtractor(new BeanWrapperFieldExtractor<ErrorTemperatura>() {{
                    setNames(new String[] {"localidad", "fecha", "temperatura"});
                }});
            }
        });

    }
}
