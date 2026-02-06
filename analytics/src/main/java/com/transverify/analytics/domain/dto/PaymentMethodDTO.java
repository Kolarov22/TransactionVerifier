package com.transverify.analytics.domain.dto;

import com.transverify.analytics.domain.enums.PaymentProcessor;
import com.transverify.analytics.domain.enums.PaymentType;
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
