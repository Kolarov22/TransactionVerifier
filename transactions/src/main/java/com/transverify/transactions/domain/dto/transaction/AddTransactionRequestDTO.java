package com.transverify.transactions.domain.dto.transaction;

import com.transverify.transactions.domain.dto.payment.PaymentMethodDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddTransactionRequestDTO {
    private String senderIBAN;
    private String receiverIBAN;
    private PaymentMethodDTO paymentMethod;
    private Double amount;

}
