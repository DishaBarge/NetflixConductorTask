package com.example.NetflixConductorTask.client;
import com.example.NetflixConductorTask.Request.WorkflowStartRequest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "netflix-conductor-service",
        url = "${spring.application.dependencies.netflix-conductor}")
public interface NetflixConductorFeignClient {
    @PostMapping("/api/workflow")
    ResponseEntity<String> startWorkflow(@RequestBody WorkflowStartRequest workflowStartRequest);
}
