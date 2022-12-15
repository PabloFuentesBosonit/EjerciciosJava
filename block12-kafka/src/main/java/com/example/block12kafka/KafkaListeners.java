package com.example.block12kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class KafkaListeners {

    @KafkaListener(
            topics = "aprendiendokafka",
            groupId = "groupId"
    )

    void listener(String data) {
        log.info("Listener receives: " + data + " look up here");
    }
}
