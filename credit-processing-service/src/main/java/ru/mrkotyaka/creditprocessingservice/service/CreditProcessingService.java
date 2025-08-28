package ru.mrkotyaka.creditprocessingservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.mrkotyaka.creditprocessingservice.event.CreditApplicationEvent;
import ru.mrkotyaka.creditprocessingservice.event.CreditDecisionEvent;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class CreditProcessingService {
    private final RabbitTemplate rabbitTemplate;
    @Value("${rabbitmq.queue.credit-decisions}")
    private String decisionQueue;

    @KafkaListener(topics = "${kafka.topic.credit-applications}", groupId = "${kafka.topic.credit-processing}", containerFactory = "kafkaListenerContainerFactory")
    public void processApplication(CreditApplicationEvent event) {
        BigDecimal monthlyPayment = calculateMonthlyPayment(event.getAmount(), event.getTerm());
        BigDecimal totalMonthlyPayments = monthlyPayment.add(event.getCurrentCreditLoad());
        boolean approved = totalMonthlyPayments.compareTo(event.getIncome().multiply(BigDecimal.valueOf(0.5))) <= 0;

        CreditDecisionEvent decision = new CreditDecisionEvent(event.getApplicationId(), approved ? "APPROVED" : "REJECTED", approved);

//        decision.setApplicationId(event.getApplicationId());
//        decision.setDecision(approved ? "APPROVED" : "REJECTED");

//        creditApplicationRepository.findById(event.getApplicationId())
//                .ifPresent(creditApplication -> {
//                    creditApplication.setStatus(event.isApproved() ?
//                            ApplicationStatus.APPROVED : ApplicationStatus.REJECTED);
//                    creditApplicationRepository.save(creditApplication);
//                });

        rabbitTemplate.convertAndSend(decisionQueue, decision);
    }

    private BigDecimal calculateMonthlyPayment(BigDecimal amount, Integer term) {
        return amount.divide(BigDecimal.valueOf(term), 2, RoundingMode.HALF_UP);
    }
}
