package com.example.NetflixConductorTask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.example.NetflixConductorTask.client")
public class NetflixConductorTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetflixConductorTaskApplication.class, args);
	}
}
