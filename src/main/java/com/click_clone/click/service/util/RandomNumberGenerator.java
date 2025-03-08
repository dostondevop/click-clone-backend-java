package com.click_clone.click.service.util;

import lombok.experimental.UtilityClass;

import java.util.Random;

@UtilityClass
public class RandomNumberGenerator {
    private static final Random random = new Random();

    public static int generateOTPCode() {
        return 100000 + random.nextInt(1000000);
    }
}