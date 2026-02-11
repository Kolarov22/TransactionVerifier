package com.transverify.analytics.controllers;

import com.transverify.analytics.domain.dto.TransactionDTO;

import com.transverify.analytics.services.AnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final AnalyticsService analyticsService;

    @PostMapping()
    public ResponseEntity<List<UUID>> analyzeBulkTransactions(@RequestBody List<TransactionDTO> transactionsToBeVerified){
        var suspiciousTransactions = analyticsService.findSuspiciousTransactions(transactionsToBeVerified);

        return ResponseEntity.ok(suspiciousTransactions);
    }
}
