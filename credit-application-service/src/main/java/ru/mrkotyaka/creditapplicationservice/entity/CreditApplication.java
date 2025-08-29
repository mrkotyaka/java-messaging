package ru.mrkotyaka.creditapplicationservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.mrkotyaka.creditapplicationservice.model.ApplicationStatus;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "credit_applications")
public class CreditApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;
    private Integer term;
    private BigDecimal income;

    @Column(name = "current_credit_load")
    private BigDecimal currentCreditLoad;

    @Column(name = "credit_rating")
    private Integer creditRating;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status = ApplicationStatus.PROCESSING;
}
