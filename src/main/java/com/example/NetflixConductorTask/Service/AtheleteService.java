package com.example.NetflixConductorTask.Service;

import com.example.NetflixConductorTask.AtheletePojo.Athelete;
import com.example.NetflixConductorTask.AtheleteRepo.AtheleteRepository;
import com.example.NetflixConductorTask.Request.WorkflowStartRequest;
import com.example.NetflixConductorTask.client.AbstractClient;
import com.example.NetflixConductorTask.exception.AtheleteInfoNotFoundException;
import com.example.NetflixConductorTask.exception.AtheleteServiceException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AtheleteService {

    private final Logger logger = LoggerFactory.getLogger(AtheleteService.class);
    @Autowired
    AtheleteRepository atheleteRepo;

    @Value("${spring.application.dependencies.netflix-conductor-ui-base-uri}")
    private String workflowBaseUri;

    private final AbstractClient abstractClient;

    public ResponseEntity<List<Athelete>> getAllAthelete() {
        logger.info("Start - AtheleteService : getAllAthelete ");
        try{
            List<Athelete> listAthelete= atheleteRepo.findAll();
            if(listAthelete.size()>0) {
                logger.info("End - AtheleteService : getAllAthelete ");
                return ResponseEntity.ok(listAthelete);
            }
            log.error("Record Empty,No Athelete's");
            return ResponseEntity.noContent().build();
        }catch (Exception ex) {
            log.error("An error occurred while fetching Athelete's", ex);
            throw new AtheleteServiceException("An error occurred while fetching Athelete's",ex);
        }
    }

    public Athelete createAthelete(Athelete athelete) {
        logger.info("Start - AtheleteService : createAthelete ");
        try {
            Athelete atheleteSaved = atheleteRepo.save(athelete);
            WorkflowStartRequest workflowStartRequest =
                    WorkflowStartRequest.builder().name("athelete_0001").input(atheleteSaved).build();
            log.debug("Workflow Request: {} ", workflowStartRequest);
            String workflowId = abstractClient.startWorkflow(workflowStartRequest);
            log.debug("WorkFlowId: {} ", workflowId);
            String workflowUri = workflowBaseUri + "/execution/{workflowId}";
            workflowUri = workflowUri.replaceAll("\\{workflowId}", workflowId);
            log.debug("WorkFlowUri: {} ", workflowUri);
            logger.info("End - AtheleteService : createAthelete ");
            return atheleteSaved;
        }catch (FeignException fe) {
            log.error("Feign exception occurred while creating Athelete for the Id: {}", athelete.getId(), fe);
            throw fe;
        } catch (RuntimeException ex) {
            log.error("Unable to Create Athelete for the Id: {}", athelete.getId());
            throw new AtheleteServiceException("Unable to Create athelete record.", ex);
        }
    }

    public String updateAthelete(Athelete getAthelete,String id) {
        logger.info("Start - AtheleteService : updateAthelete ");
        Optional<Athelete> atheletePresent = atheleteRepo.findById(id);
        if(atheletePresent.isPresent()) {
                atheletePresent.get().setTimeStamp(new Date(System.currentTimeMillis()));
                atheletePresent.get().setStatus(getAthelete.getStatus());
                atheleteRepo.save(atheletePresent.get());
                logger.info("End - AtheleteService : updateAthelete ");
                return "Updated Athelete";
        }
        log.error("No Athelete present to Update with the id: {}", id);
        throw new AtheleteInfoNotFoundException("No Athelete present to Update with the id: "+id);
    }

    public String deleteAthelete(String id) {
        logger.info("Start - AtheleteService : deleteAthelete ");
        Optional<Athelete> atheletePresent = atheleteRepo.findById(id);
        if(atheletePresent.isPresent()) {
            atheleteRepo.deleteById(id);
            logger.info("End - AtheleteService : deleteAthelete ");
            return "Athelete Deleted";
        }
        log.error("No Athelete exist to delete with the id: {} ",id);
        return "No Athelete exist to delete with specified Id: " +id;
    }
}
