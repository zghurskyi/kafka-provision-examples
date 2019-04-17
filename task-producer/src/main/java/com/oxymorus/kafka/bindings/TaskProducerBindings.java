package com.oxymorus.kafka.bindings;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface TaskProducerBindings {

    String TASKS_CHANNEL = "tasks";

    String NOTIFICATIONS_CHANNEL = "notifications";

    @Output(TASKS_CHANNEL)
    MessageChannel tasks();

    @Input(NOTIFICATIONS_CHANNEL)
    MessageChannel notifications();
}
