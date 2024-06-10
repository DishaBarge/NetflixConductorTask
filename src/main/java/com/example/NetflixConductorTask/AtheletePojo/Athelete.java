package com.example.NetflixConductorTask.AtheletePojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document(collection="athelete")
public class Athelete {

    @Id
    @NotBlank(message = "Id cannot be null or empty")
    String id;
    @Size(min=1,message="Name is blank")
    String name;
    String age;
    String gender;
    String status;
    Date timeStamp;
}
