package com.bpmn.myflowable;

import org.flowable.common.engine.api.delegate.event.FlowableEvent;
import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;

import static org.flowable.common.engine.api.delegate.event.FlowableEngineEventType.JOB_EXECUTION_FAILURE;
import static org.flowable.common.engine.api.delegate.event.FlowableEngineEventType.JOB_EXECUTION_SUCCESS;

public class FlowableEventListener   implements TaskListener {

    @Override
    public void notify(DelegateTask delegateTask) {
        String processInstanceId = delegateTask.getProcessInstanceId();
        System.out.println("李四审批"+processInstanceId);
    }
}
