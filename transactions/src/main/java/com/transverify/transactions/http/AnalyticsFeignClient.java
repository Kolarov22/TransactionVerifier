package com.transverify.transactions.http;

import com.transverify.transactions.domain.dto.transaction.TransactionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@FeignClient("ANALYTICS")
public interface AnalyticsFeignClient {

    @PostMapping("/api/transactions")
    ResponseEntity<List<UUID>> analyzeBulkTransactions(@RequestBody List<TransactionDTO> transactionsToBeVerified);
}
