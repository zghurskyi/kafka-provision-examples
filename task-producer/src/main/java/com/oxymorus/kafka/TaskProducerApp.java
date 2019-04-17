package com.oxymorus.kafka;

import io.github.zghurskyi.kafka.EnableTopicProvisioning;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableTopicProvisioning
@EnableScheduling
public class TaskProducerApp {

    public static void main(String[] args) {
        SpringApplication.run(TaskProducerApp.class, args);
    }

}
