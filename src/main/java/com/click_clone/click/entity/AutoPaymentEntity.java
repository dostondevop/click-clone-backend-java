package com.click_clone.click.entity;

import com.click_clone.click.entity.enums.AutoPayType;
import com.click_clone.click.entity.enums.DayOfWeek;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class AutoPaymentEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private AutoPayType autoPayType;

    private DayOfWeek dayOfWeek;
    private int day;

    @Column(nullable = false)
    private String time;

    @Column(nullable = false, precision = 30, scale = 0)
    private BigDecimal amount;

    @OneToMany(cascade = CascadeType.ALL)
    private List<InputValue> inputValues;

    @ManyToOne
    @JoinColumn(nullable = false)
    private ServiceEntity service;

    @ManyToOne
    @JoinColumn(nullable = false)
    private CardEntity card;
}