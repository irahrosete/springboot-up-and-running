package com.example.springbootupandrunning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class SpringbootUpAndRunningApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootUpAndRunningApplication.class, args);
    }

}