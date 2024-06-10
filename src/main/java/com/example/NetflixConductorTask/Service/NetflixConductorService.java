package com.example.NetflixConductorTask.Service;

import com.example.NetflixConductorTask.Request.WorkflowStartRequest;
import com.example.NetflixConductorTask.client.AbstractClient;
import com.example.NetflixConductorTask.client.NetflixConductorFeignClient;
import com.example.NetflixConductorTask.exception.WorkFlowException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class NetflixConductorService implements AbstractClient {

    private final NetflixConductorFeignClient netflixConductorFeignClient;
    private final Logger logger = LoggerFactory.getLogger(NetflixConductorService.class);

    @Override
    public String startWorkflow(WorkflowStartRequest workflowRequest) {
        logger.info("Start - NetflixConductorService : startWorkflow ");
        ResponseEntity<String> startWorkflowResponse;
        try {
            startWorkflowResponse = netflixConductorFeignClient.startWorkflow(workflowRequest);
        } catch (RuntimeException ex) {
            log.error("Error occurred while attempt to start the workflow. {}", workflowRequest);
            throw new WorkFlowException("Unable to start the workflow.", ex);
        }
        if (!startWorkflowResponse.getStatusCode().is2xxSuccessful()) {
            log.error("Unable to start the workflow: {}. Start workflow response: {}", workflowRequest,startWorkflowResponse);
        }
        logger.info("End - NetflixConductorService : startWorkflow ");
        return startWorkflowResponse.getBody();
    }
}
