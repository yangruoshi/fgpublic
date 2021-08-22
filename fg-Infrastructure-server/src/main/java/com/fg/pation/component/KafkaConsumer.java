package com.fg.pation.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaConsumer {
    @KafkaListener(topics = "quickstart-events")
    public void processMessage(String content){
        System.out.println("Message received: " + content);
    }
}
