package com.transverify.transactions.domain.entities;

import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY,  cascade = CascadeType.ALL)
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne(fetch = FetchType.LAZY,   cascade = CascadeType.ALL)
    @JoinColumn(name = "receiver_id")
    private User receiver;

    @OneToOne(cascade = CascadeType.ALL)
    private PaymentMethod paymentInfo;

    private Double amount;

    private Boolean fraudFlag;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false,  nullable = false)
    private LocalDateTime timestamp;
}
