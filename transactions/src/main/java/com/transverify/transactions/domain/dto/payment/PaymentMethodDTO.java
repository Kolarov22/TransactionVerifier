package com.transverify.transactions.domain.dto.payment;

import com.transverify.transactions.domain.enums.PaymentProcessor;
import com.transverify.transactions.domain.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMethodDTO {
    private PaymentType type;
    private PaymentProcessor paymentProcessor;

}
