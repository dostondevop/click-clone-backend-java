package com.click_clone.click.service;

import com.click_clone.click.entity.AttachmentEntity;
import com.click_clone.click.entity.CardEntity;
import com.click_clone.click.entity.enums.CurrencyType;
import com.click_clone.click.exception.RecordNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.click_clone.click.repository.CardRepository;

import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;
    private final UserService userService;

    public List<CardEntity> getCardList() {
        return cardRepository
                .findAllByUser_Id(userService.getCurrentUser().getId());
    }

    public CardEntity getCard(UUID cardId) {
        return cardRepository.findById(cardId)
                .orElseThrow(() -> new RecordNotFoundException("Card not found."));
    }

    public CardEntity getMainCard() {
        return cardRepository.findByUser_IdAndMainIsTrue(userService.getCurrentUser().getId())
                .orElseThrow(() -> new RecordNotFoundException("Card not found."));
    }

    public String getTotalBalance() {
        List<CardEntity> cardEntities = cardRepository
                .findAllByConsiderInTotalBalanceIsTrueAndCurrencyType(CurrencyType.UZS);
        BigInteger sum = cardEntities.stream().map(CardEntity::getBalance)
                .reduce(BigInteger.ZERO, BigInteger::add);

        return convertBigIntToString(sum);
    }

    public CardEntity create(CardEntity card) {
        return cardRepository.save(card);
    }

    public CardEntity addImageTo(UUID id, AttachmentEntity image) {
        CardEntity cardEntity = cardRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Card not found."));
        cardEntity.setBankImage(image);
        return cardRepository.save(cardEntity);
    }

    public CardEntity updateCard(UUID id,
                           String cardName,
                           boolean considerInTotalBalance,
                           boolean monitoring) {
        CardEntity cardEntity = cardRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Card not found."));

        cardEntity.setCardName(cardName);
        cardEntity.setConsiderInTotalBalance(considerInTotalBalance);
        cardEntity.setMonitoring(monitoring);

        return cardRepository.save(cardEntity);
    }

    public void deleteCard(UUID cardId) {
        cardRepository.deleteById(cardId);
    }

    private String convertBigIntToString(BigInteger balance) {
        String sumString = balance.toString();
        if (sumString.length() <= 3) {
            return "0," + sumString;
        }
        int length = sumString.length();
        return sumString.substring(0, length - 3) + "," +
                sumString.substring(length - 3);
    }
}