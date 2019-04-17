package com.oxymorus.kafka.message;

import com.oxymorus.kafka.domain.Task;
import lombok.*;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class TaskMessage {
    @NotNull
    private Task task;
}
