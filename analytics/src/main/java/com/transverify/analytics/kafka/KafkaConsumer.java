package com.transverify.analytics.kafka;

import com.transverify.analytics.domain.dto.TransactionDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumer {
    @KafkaListener(topics="transactions", containerFactory = "kafkaListenerContainerFactory")
    public void consumeEvent(TransactionDTO event) {
        try {
            log.info("Received transaction event: {}", event);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
