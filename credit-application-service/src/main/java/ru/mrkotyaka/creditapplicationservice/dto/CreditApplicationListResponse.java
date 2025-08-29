package ru.mrkotyaka.creditapplicationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.mrkotyaka.creditapplicationservice.model.ApplicationStatus;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreditApplicationListResponse {
    private Long id;
    private ApplicationStatus status;
}
