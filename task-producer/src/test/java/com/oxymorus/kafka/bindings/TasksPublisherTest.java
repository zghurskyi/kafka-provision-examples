package com.oxymorus.kafka.bindings;

import com.oxymorus.kafka.domain.Action;
import com.oxymorus.kafka.domain.Task;
import com.oxymorus.kafka.message.TaskMessage;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.Message;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.BlockingQueue;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.springframework.cloud.stream.test.matcher.MessageQueueMatcher.receivesPayloadThat;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class TasksPublisherTest {

    private static final Task TASK = Task.of(Action.SLEEP);
    private static final TaskMessage TASK_MESSAGE = TaskMessage.of(TASK);

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    @Autowired
    private MessageCollector messageCollector;
    @Autowired
    private TaskProducerBindings bindings;
    @Autowired
    private TasksPublisher publisher;

    @Test
    public void taskMessageIsPublished() {
        publisher.publish(TASK);
        assertThat(tasks(), receivesPayloadThat(is(TASK_MESSAGE)));
    }

    private BlockingQueue<Message<?>> tasks() {
        return messageCollector.forChannel(bindings.tasks());
    }
}
