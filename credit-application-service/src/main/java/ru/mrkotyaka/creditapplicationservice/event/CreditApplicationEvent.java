package ru.mrkotyaka.creditapplicationservice.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.mrkotyaka.creditapplicationservice.model.ApplicationStatus;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreditApplicationEvent {
    private Long applicationId;
    private BigDecimal amount;
    private Integer term;
    private BigDecimal income;
    private BigDecimal currentCreditLoad;
    private Integer creditRating;
//    private ApplicationStatus status;
}
