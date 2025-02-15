package com.click_clone.click.entity;

import com.click_clone.click.entity.enums.CardType;
import com.click_clone.click.entity.enums.CurrencyType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigInteger;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
public class  CardEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    @Size(min = 16, max = 16)
    private String cardNumber;

    @Column(nullable = false)
    private String expiryDate;

    private String cardName;

    @JoinColumn(nullable = false)
    @ManyToOne
    private AttachmentEntity bankImage;

    @ManyToOne
    private AttachmentEntity cardTypeImage;

    private boolean considerInTotalBalance = true;
    private boolean monitoring = false;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CardType cardType;

    @Column(precision = 30, scale = 0)
    private BigInteger balance = BigInteger.valueOf(0);

    private boolean main = false;

    @Enumerated(EnumType.STRING)
    private CurrencyType currencyType;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private UserEntity user;
}