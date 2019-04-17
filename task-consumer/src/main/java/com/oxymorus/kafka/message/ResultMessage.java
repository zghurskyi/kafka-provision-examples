package com.oxymorus.kafka.message;

import com.oxymorus.kafka.domain.Action;
import com.oxymorus.kafka.domain.Status;
import lombok.*;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ResultMessage {
    @NotNull
    private Action action;
    @NotNull
    private Status status;
}
