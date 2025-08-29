package ru.mrkotyaka.creditapplicationservice.dto;

import lombok.*;
import ru.mrkotyaka.creditapplicationservice.model.ApplicationStatus;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreditApplicationRequest {
    private BigDecimal amount;
    private Integer term;
    private BigDecimal income;
    private BigDecimal currentCreditLoad;
    private Integer creditRating;
//    private ApplicationStatus status;
}
