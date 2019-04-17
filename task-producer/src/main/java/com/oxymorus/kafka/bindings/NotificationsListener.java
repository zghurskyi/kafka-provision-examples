package com.oxymorus.kafka.bindings;

import com.oxymorus.kafka.bindings.message.NotificationMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@Validated
@EnableBinding(TaskProducerBindings.class)
public class NotificationsListener {

    @StreamListener(TaskProducerBindings.NOTIFICATIONS_CHANNEL)
    public void handleNotification(@Valid NotificationMessage notification) {
        log.info("Received: {}", notification);
    }
}
