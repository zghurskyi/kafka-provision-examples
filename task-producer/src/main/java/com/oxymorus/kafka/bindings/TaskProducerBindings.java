package com.oxymorus.kafka.bindings;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface TaskProducerBindings {

    String TASKS_CHANNEL = "tasks";

    String RESULTS_CHANNEL = "results";

    @Output(TASKS_CHANNEL)
    MessageChannel tasks();

    @Input(RESULTS_CHANNEL)
    MessageChannel results();
}
