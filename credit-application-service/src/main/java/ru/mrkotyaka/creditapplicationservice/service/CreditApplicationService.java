package ru.mrkotyaka.creditapplicationservice.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.mrkotyaka.creditapplicationservice.dto.CreditApplicationRequest;
import ru.mrkotyaka.creditapplicationservice.entity.CreditApplication;
import ru.mrkotyaka.creditapplicationservice.event.CreditApplicationEvent;
import ru.mrkotyaka.creditapplicationservice.event.CreditDecisionEvent;
import ru.mrkotyaka.creditapplicationservice.model.ApplicationStatus;
import ru.mrkotyaka.creditapplicationservice.repository.CreditApplicationRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreditApplicationService {
    private final CreditApplicationRepository creditApplicationRepository;
    private final KafkaTemplate<String, CreditApplicationEvent> kafkaTemplate;

    @Value("${kafka.topic.credit-applications}")
    private String topic;

    public Long createApplication(CreditApplicationRequest request) {
        CreditApplication creditApplication = new CreditApplication();
        BeanUtils.copyProperties(request, creditApplication);
        creditApplication = creditApplicationRepository.save(creditApplication);

        CreditApplicationEvent event = new CreditApplicationEvent(
                creditApplication.getId(),
                creditApplication.getAmount(),
                creditApplication.getTerm(),
                creditApplication.getIncome(),
                creditApplication.getCurrentCreditLoad(),
                creditApplication.getCreditRating()
//                creditApplication.getStatus() //попробовать убрать
        );

        kafkaTemplate.send(topic, event);
        return creditApplication.getId();
    }

    public ApplicationStatus getApplicationStatus(Long id) {
        return creditApplicationRepository.findById(id)
                .map(CreditApplication::getStatus)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

//    public Optional<ApplicationStatus> getStatus(Long id) {
//        return creditApplicationRepository.findById(id)
//                .map(CreditApplication::getStatus);
//    }

//    @Transactional
    @RabbitListener(queues = "${rabbitmq.queue.credit-decisions}")
    public void handlerCreditDecision(CreditDecisionEvent event) {
        creditApplicationRepository.findById(event.getApplicationId())
                .ifPresent(creditApplication -> {
                    creditApplication.setStatus(event.isApproved() ?
                            ApplicationStatus.APPROVED : ApplicationStatus.REJECTED);

                    System.out.println("status: " + event.isApproved());

                    creditApplicationRepository.save(creditApplication);
                });
    }

//    public List<CreditApplicationListResponse> getAllApplications() {
//        return creditApplicationRepository.findAll().stream()
//                .map(app -> new CreditApplicationListResponse(
//                        app.getId(), app.getStatus()))
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//    }
}
