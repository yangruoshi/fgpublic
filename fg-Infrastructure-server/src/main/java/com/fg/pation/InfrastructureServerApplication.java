package com.fg.pation;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
@AllArgsConstructor
@Slf4j
public class InfrastructureServerApplication {
    @Autowired
    private final KafkaTemplate<String, String> kakfaProducer;
    @Autowired
    private final KafkaProperties kafkaProperties;
    public static void main(String[] args) {

        SpringApplication.run(InfrastructureServerApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner()
    {
        return (args)->
        {
            log.info("kafka");
            kakfaProducer.send(
                    "quickstart-events", "java 来消息了");


        };
    }

    @RequestMapping("/mst")
    public String Get() {
        return "aa";
    }
}
