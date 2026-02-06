package com.transverify.transactions.kafka;

import com.transverify.transactions.domain.dto.transaction.TransactionDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducer {
    private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);
    private final KafkaTemplate<String, TransactionDTO> kafkaTemplate;

    public void produceTransactionEvent(TransactionDTO transaction) {
        try {
            kafkaTemplate.send("transactions", transaction);
        } catch (Exception e) {
            log.error("Error while producing transaction event for transaction: {}", e.getMessage());
        }
    }

}
