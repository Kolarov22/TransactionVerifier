package com.transverify.transactions.services;

import com.transverify.transactions.domain.dto.transaction.TransactionDTO;
import com.transverify.transactions.domain.entities.Transaction;
import com.transverify.transactions.kafka.KafkaProducer;
import com.transverify.transactions.mappers.TransactionMapper;
import com.transverify.transactions.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final KafkaProducer kafkaProducer;

    public Transaction addTransaction(Transaction transaction) {
        Transaction savedTransaction =  transactionRepository.save(transaction);
        TransactionDTO event = TransactionMapper.toDTO(savedTransaction);
        kafkaProducer.produceTransactionEvent(event);

        return savedTransaction;
    }

    public List<Transaction> findAllTransactions() {
        return transactionRepository.findAll();
    }

    @Transactional
    public void markFraudulentTransactions(List<UUID> transactionIds) {
        List<Transaction> updatedTransactions = transactionRepository.findAllByIdIn(transactionIds).stream().
                map(t -> {
                    t.setFraudFlag(true);
                    return t;
                }).toList();

        transactionRepository.saveAll(updatedTransactions);
    }

    public boolean hasSuspiciousSender(Transaction transaction) {
        List<Transaction> senderTransactions = transactionRepository.findAllBySender(transaction.getSender());
        boolean suspiciousSender = senderTransactions.stream().filter(Transaction::getFraudFlag).count() > 3;

        return suspiciousSender;
    }
}
