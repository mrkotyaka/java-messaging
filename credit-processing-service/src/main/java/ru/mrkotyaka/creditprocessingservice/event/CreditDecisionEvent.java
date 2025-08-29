package ru.mrkotyaka.creditprocessingservice.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreditDecisionEvent {
    private Long applicationId;
    private boolean approved;
}
