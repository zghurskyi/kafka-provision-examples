package com.oxymorus.kafka.bindings.message;

import com.oxymorus.kafka.domain.Action;
import com.oxymorus.kafka.domain.Status;
import lombok.*;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Getter
@Setter
@ToString
public class NotificationMessage {
    @NotNull
    private Action action;
    @NotNull
    private Status status;
}
