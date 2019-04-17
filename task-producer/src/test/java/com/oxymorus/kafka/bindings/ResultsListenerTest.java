package com.oxymorus.kafka.bindings;

import com.oxymorus.kafka.domain.Action;
import com.oxymorus.kafka.domain.Status;
import com.oxymorus.kafka.message.ResultMessage;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.containsString;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class ResultsListenerTest {

    private static final ResultMessage RESULT_MESSAGE = ResultMessage.of(Action.SLEEP, Status.SKIP_THIS_TIME);
    private static final ResultMessage NULL_ACTION_RESULT_MESSAGE = ResultMessage.of(null, Status.SKIP_THIS_TIME);
    private static final ResultMessage NULL_STATUS_RESULT_MESSAGE = ResultMessage.of(Action.SLEEP, null);

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    @Rule
    public OutputCapture output = new OutputCapture();
    @Autowired
    private MessageCollector messageCollector;
    @Autowired
    private TaskProducerBindings bindings;

    @Test
    public void logIncomingResultMessage() {
        sendResult(RESULT_MESSAGE);
        this.output.expect(containsString("Received: "));
    }

    @Test
    public void validationFailsIfMessageHasNullAction() {
        this.thrown.expect(MethodArgumentNotValidException.class);
        sendResult(NULL_ACTION_RESULT_MESSAGE);
    }

    @Test
    public void validationFailsIfMessageHasNullStatus() {
        this.thrown.expect(MethodArgumentNotValidException.class);
        sendResult(NULL_STATUS_RESULT_MESSAGE);
    }

    private void sendResult(ResultMessage taskMessage) {
        bindings.results().send(MessageBuilder.withPayload(taskMessage).build());
    }
}
