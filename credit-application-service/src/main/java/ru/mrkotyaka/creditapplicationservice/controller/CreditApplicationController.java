package ru.mrkotyaka.creditapplicationservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.mrkotyaka.creditapplicationservice.dto.CreditApplicationListResponse;
import ru.mrkotyaka.creditapplicationservice.dto.CreditApplicationRequest;
import ru.mrkotyaka.creditapplicationservice.dto.CreditApplicationResponse;
import ru.mrkotyaka.creditapplicationservice.model.ApplicationStatus;
import ru.mrkotyaka.creditapplicationservice.service.CreditApplicationService;

import java.util.List;

@RestController
@RequestMapping("/api/credit-applications")
@RequiredArgsConstructor
public class CreditApplicationController {
    private final CreditApplicationService creditApplicationService;

    @PostMapping
    public CreditApplicationResponse createApplication(@RequestBody CreditApplicationRequest creditApplicationRequest) {
        Long id = creditApplicationService.createApplication(creditApplicationRequest);
        return new CreditApplicationResponse(id);
    }

    @GetMapping("/{id}/status")
    public ApplicationStatus getApplicationStatus(@PathVariable Long id) {
        return creditApplicationService.getApplicationStatus(id);
    }

    @GetMapping
    public List<CreditApplicationListResponse> getAllApplications() {
        return creditApplicationService.getAllApplications();
    }
}
