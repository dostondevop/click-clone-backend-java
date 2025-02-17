package com.click_clone.click.service.util;

import lombok.experimental.UtilityClass;

import java.util.Random;

@UtilityClass
public class RandomNumberGenerator {
    public static int generateSixDigitNumber(int minBound, int maxBound) {
        Random random = new Random();
        return minBound + random.nextInt(maxBound);
    }
}