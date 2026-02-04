package com.transverify.transactions.domain.dto.transaction;

import com.transverify.transactions.domain.dto.payment.PaymentMethodDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {
    private UUID id;
    private String senderIBAN;
    private String receiverIBAN;
    private PaymentMethodDTO paymentMethod;
    private Double amount;
    private Boolean fraudFlag;
    private LocalDateTime timestamp;
}
