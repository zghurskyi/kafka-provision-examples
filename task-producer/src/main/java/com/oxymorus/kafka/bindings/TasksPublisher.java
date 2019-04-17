package com.oxymorus.kafka.bindings;

import com.oxymorus.kafka.domain.Task;
import com.oxymorus.kafka.message.TaskMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

@Slf4j
@EnableBinding(TaskProducerBindings.class)
public class TasksPublisher {

    private final MessageChannel tasksChannel;

    public TasksPublisher(TaskProducerBindings bindings) {
        this.tasksChannel = bindings.tasks();
    }

    public void publish(Task task) {
        TaskMessage payload = TaskMessage.of(task);
        tasksChannel.send(MessageBuilder.withPayload(payload).build());
        log.info("Published: {}", payload);
    }
}
