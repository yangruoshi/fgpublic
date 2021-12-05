package com.bpmn.myflowable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;
import java.util.zip.ZipInputStream;

import com.bpmn.myflowable.domain.Approval;
import com.bpmn.myflowable.domain.Article;
import org.apache.commons.io.IOUtils;
import org.flowable.bpmn.BpmnAutoLayout;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.engine.*;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.image.ProcessDiagramGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController

public class ArticleWorkflowController {
    @Autowired
    private ArticleWorkflowService service;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private ProcessEngine processEngine;



    @PostMapping("/submit")
    public void submit(@RequestBody Article article) {

        service.startProcess(article);
    }
    public  void  deploy() throws FileNotFoundException {
        String barFileName = "path/to/process-one.bar";
        ZipInputStream inputStream = new ZipInputStream(new FileInputStream(barFileName));
        repositoryService.createDeployment()
                .name("process-one.bar")
                .addZipInputStream(inputStream)
                .deploy();
    }

    @GetMapping("/tasks")
    public List<Article> getTasks(@RequestParam String assignee) {
        return service.getTasks(assignee);
    }

    @PostMapping("/review")
    public void review(@RequestBody Approval approval) {
        service.submitReview(approval);
    }

    @PostMapping("/review01")
    public void review01(@RequestBody Approval approval) {
        service.submit01(approval);
    }

    @PostMapping("/review02")
    public void review02(@RequestBody Approval approval) {
        service.submit02(approval);
    }

    @RequestMapping("/diagram")
    public String genProcessDiagram(
            HttpServletResponse httpServletResponse
    ) throws Exception {
        ProcessDefinition processDefinition = repositoryService
                .createProcessDefinitionQuery()
                .processDefinitionKey("articleReview1").singleResult();
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinition.getId());
        BpmnAutoLayout bpmnAutoLayout = new BpmnAutoLayout(bpmnModel);
        bpmnAutoLayout.setTaskHeight(120);
        bpmnAutoLayout.setTaskWidth(120);
        bpmnAutoLayout.execute();
        ProcessEngineConfiguration configuration =
                processEngine.getProcessEngineConfiguration();
        ProcessDiagramGenerator diagramGenerator = configuration.getProcessDiagramGenerator();
        InputStream inputStream = diagramGenerator.generateDiagram
                (bpmnModel, "png", Collections.emptyList(),
                Collections.emptyList(), "宋体", "宋体", "宋体",
                this.getClass().getClassLoader(), 1.0, true);
        try {
            byte[] bytes= IOUtils.toByteArray(inputStream);
            return "data:image/png;base64," +Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
              IOUtils.closeQuietly(inputStream);
            } catch (Exception ignored) {
            }
        }
        return  null;

    }

}
