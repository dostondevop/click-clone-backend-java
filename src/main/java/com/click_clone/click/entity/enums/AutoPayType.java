package com.click_clone.click.entity.enums;

import lombok.Getter;

@Getter
public enum AutoPayType {
    DAY_OF_WEEK("day of week"),
    DAY_OF_MONTH("day of month"),
    LAST_DAY_OF_MONTH("last day of month");

    private final String name;

    AutoPayType(String name) {
        this.name = name;
    }
}