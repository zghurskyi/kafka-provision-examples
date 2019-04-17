package com.oxymorus.kafka;

import io.github.zghurskyi.kafka.EnableTopicProvisioning;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableTopicProvisioning
public class TaskConsumerApp {

    public static void main(String[] args) {
        SpringApplication.run(TaskConsumerApp.class, args);
    }

}
