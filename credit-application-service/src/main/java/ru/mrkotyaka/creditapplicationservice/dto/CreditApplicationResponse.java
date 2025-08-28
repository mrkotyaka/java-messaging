package ru.mrkotyaka.creditapplicationservice.dto;

import lombok.*;
import ru.mrkotyaka.creditapplicationservice.model.ApplicationStatus;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreditApplicationResponse {
    private Long id;
//    private ApplicationStatus status;
}
