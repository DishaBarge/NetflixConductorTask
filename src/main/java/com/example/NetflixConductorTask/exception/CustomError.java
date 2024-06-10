package com.example.NetflixConductorTask.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomError {

     String message;
     String details;
     LocalDateTime localDateTime;
     public CustomError(String message, String details, LocalDateTime localDateTime) {
          super();
          this.message = message;
          this.details = details;
          this.localDateTime = localDateTime;
     }
}
