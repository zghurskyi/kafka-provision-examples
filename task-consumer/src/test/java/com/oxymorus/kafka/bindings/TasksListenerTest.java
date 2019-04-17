package com.oxymorus.kafka.bindings;

import com.oxymorus.kafka.domain.Action;
import com.oxymorus.kafka.domain.Result;
import com.oxymorus.kafka.domain.Status;
import com.oxymorus.kafka.domain.Task;
import com.oxymorus.kafka.message.ResultMessage;
import com.oxymorus.kafka.message.TaskMessage;
import com.oxymorus.kafka.service.TaskExecutor;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.BlockingQueue;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.cloud.stream.test.matcher.MessageQueueMatcher.receivesPayloadThat;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class TasksListenerTest {

    private static final Task TASK = Task.of(Action.SLEEP);
    private static final Result RESULT = Result.of(Status.SKIP_THIS_TIME);
    private static final TaskMessage TASK_MESSAGE = TaskMessage.of(TASK);
    private static final TaskMessage NULL_TASK_MESSAGE = TaskMessage.of(null);
    private static final ResultMessage RESULT_MESSAGE = ResultMessage.of(TASK.getAction(), RESULT.getStatus());

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    @Autowired
    private MessageCollector messageCollector;
    @Autowired
    private TaskConsumerBindings bindings;
    @MockBean
    private TaskExecutor taskExecutor;

    @Test
    public void publishResultMessageForGivenTask() {
        when(taskExecutor.execute(TASK)).thenReturn(RESULT);
        sendTask(TASK_MESSAGE);
        assertThat(results(), receivesPayloadThat(is(RESULT_MESSAGE)));
    }

    @Test
    public void validationFailsIfMessageHasNullTask() {
        this.thrown.expect(MethodArgumentNotValidException.class);
        sendTask(NULL_TASK_MESSAGE);
    }

    private BlockingQueue<Message<?>> results() {
        return messageCollector.forChannel(bindings.results());
    }

    private void sendTask(TaskMessage taskMessage) {
        bindings.tasks().send(MessageBuilder.withPayload(taskMessage).build());
    }
}
