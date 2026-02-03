package com.transverify.transactions.domain.dto;

import com.transverify.transactions.domain.entities.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequestDTO {
    private String senderIBAN;
    private String receiverIBAN;
    private PaymentMethodDTO paymentMethod;
    private Double amount;

}
