package com.click_clone.click.entity.enums;

import lombok.Getter;

@Getter
public enum CardType {
    UZCARD("Uzcard"),
    HUMO("Humo"),
    VISA("Visa"),
    WALLET("Wallet");

    private final String name;

    CardType(String name) {
        this.name = name;
    }
}