package com.example.NetflixConductorTask.Request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WorkflowStartRequest {

    String name;
    Integer version;
    Object input;
    String correlationId;
}
