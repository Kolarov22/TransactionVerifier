package com.transverify.transactions.mappers;

import com.transverify.transactions.domain.dto.payment.PaymentMethodDTO;
import com.transverify.transactions.domain.dto.transaction.AddTransactionResponseDTO;
import com.transverify.transactions.domain.dto.transaction.TransactionDTO;
import com.transverify.transactions.domain.entities.Transaction;

public class TransactionMapper {
    public static TransactionDTO toDTO(Transaction t) {
        PaymentMethodDTO paymentMethodDTO = new PaymentMethodDTO();
        paymentMethodDTO.setPaymentProcessor(t.getPaymentInfo().getPaymentProcessor());
        paymentMethodDTO.setType(t.getPaymentInfo().getType());

        TransactionDTO dto = TransactionDTO.builder().
                id(t.getId()).
                amount(t.getAmount()).
                timestamp(t.getTimestamp()).
                senderIBAN(t.getSender().getIBAN()).
                senderName(t.getSender().getFirstName() +  " " + t.getSender().getLastName()).
                receiverIBAN(t.getReceiver().getIBAN()).
                receiverName(t.getReceiver().getFirstName() +  " " + t.getReceiver().getLastName()).
                fraudFlag(t.getFraudFlag()).
                paymentMethod(paymentMethodDTO).
                build();

        return dto;
    }

    public static AddTransactionResponseDTO toResponseDTO(Transaction t) {
        AddTransactionResponseDTO transactionResponseDTO = new AddTransactionResponseDTO();
        transactionResponseDTO.setTransactionId(t.getId());
        transactionResponseDTO.setTransactionDate(t.getTimestamp());

        return transactionResponseDTO;
    }
}
