package com.oxymorus.kafka.bindings;

import com.oxymorus.kafka.domain.Result;
import com.oxymorus.kafka.domain.Task;
import com.oxymorus.kafka.message.ResultMessage;
import com.oxymorus.kafka.message.TaskMessage;
import com.oxymorus.kafka.service.TaskExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;

import javax.validation.Valid;

@RequiredArgsConstructor
@EnableBinding(TaskConsumerBindings.class)
public class TasksListener {

    private final TaskExecutor service;

    @StreamListener(TaskConsumerBindings.TASKS_CHANNEL)
    @SendTo(TaskConsumerBindings.RESULTS_CHANNEL)
    public ResultMessage listenForTasks(@Valid @Payload TaskMessage taskMessage) {
        Task task = taskMessage.getTask();
        Result result = service.execute(task);
        return ResultMessage.of(task.getAction(), result.getStatus());
    }
}
