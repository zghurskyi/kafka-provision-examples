package com.oxymorus.kafka.service;

import com.oxymorus.kafka.domain.Result;
import com.oxymorus.kafka.domain.Status;
import com.oxymorus.kafka.domain.Task;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class TaskExecutorImpl implements TaskExecutor {
    private static final List<Status> STATUSES = Arrays.asList(Status.SUCCESS, Status.FAIL, Status.SKIP_THIS_TIME);
    private static final Random RANDOM_NUMBER_GENERATOR = new Random();
    private static final int MAX_SLEEP_MILLIS = 2000;

    @Override
    public Result execute(Task task) {
        sleepRandomly();
        Collections.shuffle(STATUSES);
        return Result.of(STATUSES.iterator().next());
    }

    private static void sleepRandomly() {
        try {
            Thread.sleep(RANDOM_NUMBER_GENERATOR.nextInt(MAX_SLEEP_MILLIS));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
