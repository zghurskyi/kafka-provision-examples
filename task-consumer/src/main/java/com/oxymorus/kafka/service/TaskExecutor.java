package com.oxymorus.kafka.service;

import com.oxymorus.kafka.domain.Result;
import com.oxymorus.kafka.domain.Task;

public interface TaskExecutor {

    Result execute(Task task);
}
