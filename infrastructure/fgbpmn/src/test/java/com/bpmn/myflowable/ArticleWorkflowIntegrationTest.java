package com.bpmn.myflowable;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.test.Deployment;
import org.flowable.spring.impl.test.FlowableSpringExtension;
import org.flowable.task.api.Task;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(FlowableSpringExtension.class)
@SpringBootTest
public class ArticleWorkflowIntegrationTest {
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;

    @Test
    @Deployment(resources = {"processes/my1.bpmn20.xml"})
    void articleApprovalTest() {
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("author", "test@baeldung.com");
        variables.put("url", "http://baeldung.com/dummy");

        runtimeService.startProcessInstanceByKey("articleReview1", variables);
        //Task task = taskService.createTaskQuery()
        //  .singleResult();
        //assertEquals("Review the submitted tutorial", task.getName());
        variables.put("approved", true);
        List<Task> tasks = taskService.createTaskQuery().taskAssignee("zhangsan")
                .list();
        System.out.println("获取张三的任务");
        tasks.forEach(v -> System.out.println(v.getId() + " "

                + v.getName() + " " + v.getTaskDefinitionKey()
                + " " + v.getExecutionId() + " " + v.getProcessInstanceId() + " " + v.getCreateTime()));
        taskService.complete(tasks.get(0).getId(), variables);

        Task taskls = taskService.createTaskQuery()
                .taskAssignee("lisi").singleResult();

        taskService.complete(taskls.getId(), variables);

        //runtimeService.createChangeActivityStateBuilder()
              //  .processInstanceId("articleReview1")
              //  .moveActivityIdTo("", "");
       /* runtimeService.createChangeActivityStateBuilder()
                .processInstanceId(proInstanceId)
                .moveActivityIdsToSingleActivityId(curTaskKeys, targetTaskKey)
                .changeState()*/
        // assertEquals(0, runtimeService.createProcessInstanceQuery()
        //     .count());
    }
}
