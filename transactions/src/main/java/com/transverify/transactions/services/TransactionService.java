package com.transverify.transactions.services;

import com.transverify.transactions.domain.entities.Transaction;
import com.transverify.transactions.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public Transaction addTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
}
