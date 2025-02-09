package com.click_clone.click.entity;

import com.click_clone.click.entity.enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TransactionEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String data;
    private BigInteger amount;
    private BigInteger cashback;
    private BigInteger commission;
    private TransactionStatus status;

    @ManyToOne
    @JoinColumn(name = "from_card_id")
    private CardEntity fromCard;

    @ManyToOne
    @JoinColumn(name = "to_card_id")
    private CardEntity toCard;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private ServiceEntity service;

    @ManyToOne
    @JoinColumn(name = "image_id")
    private AttachmentEntity image;
}
