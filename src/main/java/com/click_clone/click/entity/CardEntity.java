package com.click_clone.click.entity;

import lombok.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import com.click_clone.click.entity.enums.CardType;
import com.click_clone.click.entity.enums.CurrencyType;
import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.UUID;
import java.math.BigDecimal;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
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

    @Builder.Default
    private boolean considerInTotalBalance = true;
    @Builder.Default
    private boolean monitoring = false;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CardType cardType;

    @Column(precision = 30, scale = 10, nullable = false)
    @Builder.Default
    private BigDecimal balance = BigDecimal.ZERO;

    @Builder.Default
    private boolean main = false;

    @Enumerated(EnumType.STRING)
    private CurrencyType currencyType;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private UserEntity user;
}