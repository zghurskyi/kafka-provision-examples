package com.oxymorus.kafka.domain;

import lombok.*;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Getter
@Setter
@ToString
public class Result {
    @NotNull
    private Status status;
}
