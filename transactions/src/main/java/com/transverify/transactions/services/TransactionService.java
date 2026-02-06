package com.transverify.transactions.services;

import com.transverify.transactions.domain.dto.transaction.TransactionDTO;
import com.transverify.transactions.domain.entities.Transaction;
import com.transverify.transactions.kafka.KafkaProducer;
import com.transverify.transactions.mappers.TransactionMapper;
import com.transverify.transactions.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
