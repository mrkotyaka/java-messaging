package ru.mrkotyaka.creditapplicationservice.dto;

import lombok.*;

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
}
