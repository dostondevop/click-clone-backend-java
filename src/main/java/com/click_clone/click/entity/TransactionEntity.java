package com.click_clone.click.entity;

import com.click_clone.click.entity.enums.TransactionStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;
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

    @Column(nullable = false)
    @OneToMany(cascade = CascadeType.ALL)
    private List<InputValue> inputValues;

    @Column(precision = 30, scale = 0)
    private BigInteger amount;

    @Column(precision = 30, scale = 0)
    private BigInteger cashback;

    @Column(precision = 30, scale = 0)
    private BigInteger commission;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionStatus status;

    @ManyToOne
    @JoinColumn(name = "from_card_id", nullable = false)
    private CardEntity fromCard;

    @ManyToOne
    @JoinColumn(name = "to_card_id")
    private CardEntity toCard;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private ServiceEntity service;

    @ManyToOne
    @JoinColumn(name = "image_id", nullable = false)
    private AttachmentEntity image;
}