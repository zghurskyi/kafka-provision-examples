package com.oxymorus.kafka.bindings;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface TaskConsumerBindings {

    String TASKS_CHANNEL = "tasks";

    String NOTIFICATIONS_CHANNEL = "notifications";

    @Input(TASKS_CHANNEL)
    MessageChannel tasks();

    @Output(NOTIFICATIONS_CHANNEL)
    MessageChannel notifications();
}
