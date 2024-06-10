package com.example.NetflixConductorTask.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AtheleteInfoNotFoundException extends RuntimeException {

    public AtheleteInfoNotFoundException(String message) {
        super(message);
    }
}
