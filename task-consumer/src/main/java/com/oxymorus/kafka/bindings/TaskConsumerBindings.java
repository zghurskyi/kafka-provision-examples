package com.oxymorus.kafka.bindings;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface TaskConsumerBindings {

    String TASKS_CHANNEL = "tasks";

    String RESULTS_CHANNEL = "results";

    @Input(TASKS_CHANNEL)
    MessageChannel tasks();

    @Output(RESULTS_CHANNEL)
    MessageChannel results();
}
