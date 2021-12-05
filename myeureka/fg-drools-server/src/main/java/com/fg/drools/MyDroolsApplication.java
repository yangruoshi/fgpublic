package com.fg.drools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MyDroolsApplication {
    Logger logger=LoggerFactory.getLogger(MyDroolsApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(MyDroolsApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner()
    {
        return (args)->
        {
            logger.info("规则引擎服务");
        };
    }
}
