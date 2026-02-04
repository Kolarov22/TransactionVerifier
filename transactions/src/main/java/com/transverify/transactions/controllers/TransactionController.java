package com.transverify.transactions.controllers;

import com.transverify.transactions.domain.dto.payment.PaymentMethodDTO;
import com.transverify.transactions.domain.dto.transaction.AddTransactionRequestDTO;
import com.transverify.transactions.domain.dto.transaction.AddTransactionResponseDTO;
import com.transverify.transactions.domain.dto.transaction.TransactionDTO;
import com.transverify.transactions.domain.entities.PaymentMethod;
import com.transverify.transactions.domain.entities.Transaction;
import com.transverify.transactions.domain.entities.User;
import com.transverify.transactions.kafka.KafkaProducer;
import com.transverify.transactions.services.TransactionService;
import com.transverify.transactions.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionService transactionService;
    private final UserService userService;
    private final KafkaProducer kafkaProducer;

    @PostMapping
    public ResponseEntity<AddTransactionResponseDTO> addTransaction(@RequestBody AddTransactionRequestDTO transactionBody){
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
        kafkaProducer.produceTransactionEvent(transaction);
        AddTransactionResponseDTO transactionResponseDTO = new AddTransactionResponseDTO();
        transactionResponseDTO.setTransactionId(savedTransaction.getId());
        transactionResponseDTO.setTransactionDate(savedTransaction.getTimestamp());


        return ResponseEntity.ok(transactionResponseDTO);

    }

    @GetMapping
    public ResponseEntity<List<TransactionDTO>> findAllTransactions(){
        List<Transaction> transactions = transactionService.findAllTransactions();

        List<TransactionDTO> dtos = transactions.stream().map(t -> {
            TransactionDTO dto = new TransactionDTO();
            dto.setId(t.getId());
            dto.setAmount(t.getAmount());
            dto.setTimestamp(t.getTimestamp());
            dto.setFraudFlag(t.getFraudFlag());
            dto.setSenderIBAN(t.getSender().getIBAN());
            dto.setReceiverIBAN(t.getReceiver().getIBAN());
            PaymentMethodDTO paymentMethodDTO = new PaymentMethodDTO();
            paymentMethodDTO.setPaymentProcessor(t.getPaymentInfo().getPaymentProcessor());
            paymentMethodDTO.setType(t.getPaymentInfo().getType());
            dto.setPaymentMethod(paymentMethodDTO);
            return dto;
        }).toList();

        return ResponseEntity.ok(dtos);
    }

    



}
