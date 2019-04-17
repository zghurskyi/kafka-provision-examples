package com.oxymorus.kafka.job;

import com.oxymorus.kafka.bindings.TasksPublisher;
import com.oxymorus.kafka.domain.Action;
import com.oxymorus.kafka.domain.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Component
public class TasksPublishingJob {

    private static final int FIXED_RATE_MS = 1000;
    private static final List<Action> ACTIONS = Arrays.asList(Action.WRITE_CODE, Action.EAT, Action.SLEEP);

    private final TasksPublisher tasksPublisher;

    @Scheduled(fixedRate = FIXED_RATE_MS)
    public void tasks() {
        tasksPublisher.publish(prepareTask());
    }

    private Task prepareTask() {
        Collections.shuffle(ACTIONS);
        return Task.of(ACTIONS.iterator().next());
    }
}
