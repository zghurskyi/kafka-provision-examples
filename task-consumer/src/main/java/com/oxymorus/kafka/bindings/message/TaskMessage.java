package com.oxymorus.kafka.bindings.message;

import com.oxymorus.kafka.domain.Action;
import com.oxymorus.kafka.domain.Task;
import lombok.*;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Getter
@Setter
@ToString
public class TaskMessage {
    @NotNull
    private Task task;

    public Action findAction() {
        return task.getAction();
    }
}
