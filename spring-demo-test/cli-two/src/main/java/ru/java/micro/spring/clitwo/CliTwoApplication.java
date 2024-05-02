package ru.java.micro.spring.clitwo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CliTwoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CliTwoApplication.class, args);
	}

}
