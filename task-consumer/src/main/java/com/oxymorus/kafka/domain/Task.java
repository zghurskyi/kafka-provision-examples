package com.oxymorus.kafka.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Getter
@Setter
@ToString
public class Task {
    private Action action;
}

