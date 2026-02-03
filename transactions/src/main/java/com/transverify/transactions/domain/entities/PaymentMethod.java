package com.transverify.transactions.domain.entities;

import com.transverify.transactions.domain.enums.PaymentProcessor;
import com.transverify.transactions.domain.enums.PaymentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="payments")
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Enumerated(EnumType.STRING)
    private PaymentType type;
    @Enumerated(EnumType.STRING)
    private PaymentProcessor paymentProcessor;

}
