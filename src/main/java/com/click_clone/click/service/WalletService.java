package com.click_clone.click.service;

import com.click_clone.click.entity.AttachmentEntity;
import com.click_clone.click.entity.CardEntity;
import com.click_clone.click.entity.UserEntity;
import com.click_clone.click.entity.enums.CardType;
import com.click_clone.click.entity.enums.CurrencyType;
import com.click_clone.click.repository.AttachmentRepository;
import com.click_clone.click.repository.CardRepository;
import com.click_clone.click.service.util.AttachmentUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class WalletService {
    private final CardRepository cardRepository;
    private final AttachmentRepository attachmentRepository;
    private final Random random;

    private static final String DEFAULT_WALLET_IMAGE_URL = "/static/image/";
    private static final String DEFAULT_WALLET_IMAGE_NAME = "WALLET.png";
    private static final String DEFAULT_WALLET_START_NUMBERS = "8800";

    @Async
    public void createWallet(UserEntity user) throws IOException {
        AttachmentEntity save = attachmentRepository.save(getAttachment());

        CardEntity card = CardEntity.builder()
                .cardName("Click wallet")
                .cardType(CardType.WALLET)
                .bankImage(save)
                .currencyType(CurrencyType.UZS)
                .expiryDate("unlimited")
                .cardNumber(generateWalletNumber())
                .user(user)
                .considerInTotalBalance(true)
                .build();

        cardRepository.save(card);
    }

    private AttachmentEntity getAttachment() throws IOException {
        Optional<AttachmentEntity> byName = attachmentRepository.findByName(DEFAULT_WALLET_IMAGE_NAME);
        if (byName.isEmpty()) {
            return AttachmentUtil
                    .buildAttachmentFromDefaultImage(DEFAULT_WALLET_IMAGE_URL + DEFAULT_WALLET_IMAGE_NAME);
        }
        return byName.get();
    }

    private String generateWalletNumber() {
        StringBuilder randomCardNumber = new StringBuilder();
        for (int i = 1; i <= 12; i++) {
            randomCardNumber.append(random.nextInt(0, 10));
        }
        String cardNumber = DEFAULT_WALLET_START_NUMBERS + randomCardNumber;

        if (cardRepository.existsByCardName(cardNumber)) {
            return generateWalletNumber();
        }

        return cardNumber;
    }
}
