package com.oxymorus.kafka.bindings;

import com.oxymorus.kafka.bindings.message.NotificationMessage;
import com.oxymorus.kafka.bindings.message.TaskMessage;
import com.oxymorus.kafka.domain.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Slf4j
@Validated
@EnableBinding(TaskConsumerBindings.class)
public class TasksListener {

    private static final List<Status> STATUSES = Arrays.asList(Status.SUCCESS, Status.FAIL, Status.SKIP_THIS_TIME);
    private static final Random RANDOM_NUMBER_GENERATOR = new Random();
    private static final int MAX_SLEEP_MILLIS = 2000;

    @StreamListener(TaskConsumerBindings.TASKS_CHANNEL)
    @SendTo(TaskConsumerBindings.NOTIFICATIONS_CHANNEL)
    public NotificationMessage listenForTasks(@Valid @Payload TaskMessage task) {
        return prepareNotification(task);
    }

    private NotificationMessage prepareNotification(TaskMessage task) {
        sleepRandomly();
        Collections.shuffle(STATUSES);
        return NotificationMessage.of(task.findAction(), STATUSES.get(0));
    }

    private static void sleepRandomly() {
        try {
            Thread.sleep(RANDOM_NUMBER_GENERATOR.nextInt(MAX_SLEEP_MILLIS));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
