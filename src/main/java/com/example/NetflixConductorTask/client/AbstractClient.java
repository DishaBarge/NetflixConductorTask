package com.example.NetflixConductorTask.client;

import com.example.NetflixConductorTask.Request.WorkflowStartRequest;

public interface AbstractClient {

    String startWorkflow(WorkflowStartRequest workflowRequest);
}
