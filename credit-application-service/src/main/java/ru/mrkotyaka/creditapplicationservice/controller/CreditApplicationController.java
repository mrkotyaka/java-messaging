package ru.mrkotyaka.creditapplicationservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mrkotyaka.creditapplicationservice.dto.CreditApplicationRequest;
import ru.mrkotyaka.creditapplicationservice.dto.CreditApplicationResponse;
import ru.mrkotyaka.creditapplicationservice.entity.CreditApplication;
import ru.mrkotyaka.creditapplicationservice.model.ApplicationStatus;
import ru.mrkotyaka.creditapplicationservice.service.CreditApplicationService;

import java.util.List;
import java.util.Map;

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
    public ApplicationStatus getApplicationStatus(@PathVariable Long id){
        return creditApplicationService.getApplicationStatus(id);
    }

    @GetMapping("/v2/{id}/status")
    public ResponseEntity<Map<String, String>> status(@PathVariable Long id) {
//        return creditApplicationService.getStatus(id)
//                .map(s -> ResponseEntity.ok(Map.of("status", s.name())))
//                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "not found")));
        return null;
    }

//    @GetMapping
//    public List<CreditApplicationListResponse> getAllApplications() {
//        return creditApplicationService.getAllApplications();
//    }
}
