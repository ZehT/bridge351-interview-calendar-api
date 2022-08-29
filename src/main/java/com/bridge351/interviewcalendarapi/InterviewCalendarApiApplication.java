package com.bridge351.interviewcalendarapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@EnableCaching
@ComponentScan(lazyInit = true)
@SpringBootApplication(exclude = RepositoryRestMvcAutoConfiguration.class)
public class InterviewCalendarApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(InterviewCalendarApiApplication.class, args);
    }

}
