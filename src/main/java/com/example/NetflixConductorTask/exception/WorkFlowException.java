package com.example.NetflixConductorTask.exception;

public class WorkFlowException extends RuntimeException  {

    public WorkFlowException(String message, Throwable cause) {
        super(message, cause);
    }
}
