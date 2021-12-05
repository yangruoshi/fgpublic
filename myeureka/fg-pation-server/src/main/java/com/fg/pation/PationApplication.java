package com.fg.pation;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class PationApplication {
    public static void main(String[] args) {

        SpringApplication.run(PationApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner()
    {
        return (args)->
        {
            System.out.println("arg.....");

        };
    }

    @RequestMapping("/mst")
    public String Get() {
        return "aa";
    }
}
