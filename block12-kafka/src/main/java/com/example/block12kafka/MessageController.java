package com.example.block12kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("mensaje")
public class MessageController {

    private KafkaTemplate<String, String> kafkaTemplate;

    public MessageController(KafkaTemplate<String, String> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping
    public MessageRequest publish(@RequestBody MessageRequest request){
        kafkaTemplate.send("aprendiendokafka", request.message());
        return request;
    }

}
