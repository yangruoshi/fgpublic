package com.bpmn.myflowable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.bpmn.myflowable.domain.Approval;
import com.bpmn.myflowable.domain.Article;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ArticleWorkflowService {
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;

    @Autowired
    private RepositoryService repositoryService;

    @Transactional
    public void startProcess(Article article) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("author", article.getAuthor());
        variables.put("url", article.getUrl());

        runtimeService.startProcessInstanceByKey
                ("articleReview1", variables);
    }

    @Transactional
    public List<Article> getTasks(String assignee) {
        List<Task> tasks = taskService.createTaskQuery()
                .taskAssignee(assignee)
                .list();
        return tasks.stream()
                .map(task -> {
                    Map<String, Object> variables = taskService.getVariables(task.getId());
                    return new Article(task.getId(), (String) variables.get("author"), (String) variables.get("url"));
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public void submitReview(Approval approval) {
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("approved", approval.isStatus());
        taskService.complete(approval.getId(), variables);
    }

    @Transactional
    public void submit01(Approval approval) {
        ProcessInstance processInstance = runtimeService
                .startProcessInstanceByKey("financialReport");
        String deploymentId = repositoryService
                .createDeploymentQuery().singleResult().getId();

        System.out.println("deploymentId"+deploymentId);
            Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("approved", approval.isStatus());
        Task task = taskService.createTaskQuery()
                .taskAssignee("zhangsan")
                .singleResult();

        if (null==task ) {
            System.out.println("流程不存在");
        }
         System.out.println
                 (
                 task.getId() + " "
                + task.getName() + " " + task.getTaskDefinitionKey()
                + " " + task.getExecutionId() + " " + task.getProcessInstanceId() + " " + task.getCreateTime()

         );

         taskService.complete(task.getId(), variables);

    }


    @Transactional
    public void submit02(Approval approval) {
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("approved", approval.isStatus());
        Task taskls = taskService.createTaskQuery()
                .taskAssignee("lisi").singleResult();
        taskService.complete(taskls.getId(), variables);
    }


}
