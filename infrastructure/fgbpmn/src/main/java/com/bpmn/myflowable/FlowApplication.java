package com.bpmn.myflowable;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.test.Deployment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(proxyBeanMethods = false)
@Deployment(resources = {"processes/my1.bpmn20.xml"})
public class FlowApplication {
    @Autowired
    ProcessEngine processEngine;

    public static void main(String[] args) {
        SpringApplication.run(FlowApplication.class, args);
    }

    @Bean
    CommandLineRunner init(final RepositoryService repositoryService,
                           final RuntimeService runtimeService,
                           final TaskService taskService
    ) {
        return (x)->
        {
            System.out.println("Number of process definitions : "
                    + repositoryService.createProcessDefinitionQuery().count());
            System.out.println("Number of tasks : " + taskService.createTaskQuery().count());
            runtimeService.startProcessInstanceByKey("oneTaskProcess");
            System.out.println("Number of tasks after process start: "
                    + taskService.createTaskQuery().count());
        };
    }
}
