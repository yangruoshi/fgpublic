package com.fg.drools;

import com.fg.drools.domain.Product;
import com.fg.drools.domain.VipDiscountPrice;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

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
