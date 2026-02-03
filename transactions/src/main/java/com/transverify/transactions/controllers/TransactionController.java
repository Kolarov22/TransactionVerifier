package com.transverify.transactions.controllers;

import com.transverify.transactions.domain.dto.TransactionRequestDTO;
import com.transverify.transactions.domain.dto.TransactionResponseDTO;
import com.transverify.transactions.domain.entities.PaymentMethod;
import com.transverify.transactions.domain.entities.Transaction;
import com.transverify.transactions.domain.entities.User;
import com.transverify.transactions.services.TransactionService;
import com.transverify.transactions.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionService transactionService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<TransactionResponseDTO> addTransaction(@RequestBody TransactionRequestDTO transactionBody){
        User sender = userService.findByIBAN(transactionBody.getSenderIBAN()).orElseThrow();
        User receiver =  userService.findByIBAN(transactionBody.getReceiverIBAN()).orElseThrow();

        PaymentMethod paymentMethod= new PaymentMethod();
        paymentMethod.setPaymentProcessor(transactionBody.getPaymentMethod().getPaymentProcessor());
        paymentMethod.setType(transactionBody.getPaymentMethod().getType());

        Transaction transaction = new Transaction();
        transaction.setSender(sender);
        transaction.setReceiver(receiver);
        transaction.setAmount(transactionBody.getAmount());
        transaction.setPaymentInfo(paymentMethod);
        transaction.setFraudFlag(false);

        Transaction savedTransaction = transactionService.addTransaction(transaction);

        TransactionResponseDTO transactionResponseDTO = new TransactionResponseDTO();
        transactionResponseDTO.setTransactionId(savedTransaction.getId());
        transactionResponseDTO.setTransactionDate(savedTransaction.getTimestamp());


        return ResponseEntity.ok(transactionResponseDTO);

    }

}
