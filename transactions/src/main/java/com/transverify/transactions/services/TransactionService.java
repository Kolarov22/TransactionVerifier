package com.transverify.transactions.services;

import com.transverify.transactions.domain.entities.Transaction;
import com.transverify.transactions.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public Transaction addTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public List<Transaction> findAllTransactions() {
        return transactionRepository.findAll();
    }
}
