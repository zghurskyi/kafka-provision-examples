package com.oxymorus.kafka.bindings;

import com.oxymorus.kafka.message.ResultMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@RequiredArgsConstructor
@Slf4j
@Validated
@EnableBinding(TaskProducerBindings.class)
public class ResultsListener {

    @StreamListener(TaskProducerBindings.RESULTS_CHANNEL)
    public void handleNotification(@Valid @Payload ResultMessage resultMessage) {
        log.info("Received: {}", resultMessage);
    }
}
