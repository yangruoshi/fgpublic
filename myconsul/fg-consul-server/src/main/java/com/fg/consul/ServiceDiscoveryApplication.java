package com.fg.consul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.consul.ConsulAutoConfiguration;


@SpringBootApplication
@EnableDiscoveryClient
public class ServiceDiscoveryApplication {

    public static void main(String[] args) {
      SpringApplication.run(ServiceDiscoveryApplication.class,args);

    }



}
