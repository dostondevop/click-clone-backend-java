package com.click_clone.click.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.click_clone.click.entity.CardEntity;
import com.click_clone.click.entity.UserEntity;
import com.click_clone.click.service.util.MessageUtil;
import com.click_clone.click.entity.enums.CurrencyType;
import com.click_clone.click.repository.UserRepository;
import com.click_clone.click.repository.CardRepository;
import com.click_clone.click.exception.RecordNotFoundException;

import java.util.List;
import java.util.UUID;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final CurrencyService currencyService;

    public List<CardEntity> getCardList() {
        return cardRepository
                .findAllByUser_Id(userService.getCurrentUser().getId());
    }

    public CardEntity getCard(UUID cardId) {
        return cardRepository.findById(cardId)
                .orElseThrow(() -> new RecordNotFoundException(MessageUtil.CARD_NOT_FOUND_ERROR));
    }

    public CardEntity getMainCard() {
        return cardRepository.findByUser_IdAndMainIsTrue(userService.getCurrentUser().getId())
                .orElseThrow(() -> new RecordNotFoundException(MessageUtil.CARD_NOT_FOUND_ERROR));
    }

    public String getTotalBalance() {
        List<CardEntity> cardEntities = cardRepository
                .findAllByConsiderInTotalBalanceIsTrue();
        BigDecimal sum = cardEntities.stream().map(this::getCardBalance)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return sum.toString();
    }

    private BigDecimal getCardBalance(CardEntity card) {
        if (card.getCurrencyType().equals(CurrencyType.USD)) {
            return currencyService.getExchangedRateAsBigDecimal().multiply(card.getBalance());
        }
        return card.getBalance();
    }

    public CardEntity create(CardEntity card) {
        card.setConsiderInTotalBalance(true);
        return cardRepository.save(card);
    }

//    public CardEntity addImageTo(UUID id, AttachmentEntity image) {
//        CardEntity cardEntity = cardRepository.findById(id)
//                .orElseThrow(() -> new RecordNotFoundException("Card not found."));
//        cardEntity.setBankImage(image);
//        return cardRepository.save(cardEntity);
//    }

    public CardEntity updateCard(UUID id,
                           String cardName,
                           boolean considerInTotalBalance,
                           boolean monitoring) {
        CardEntity cardEntity = cardRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(MessageUtil.CARD_NOT_FOUND_ERROR));

        cardEntity.setCardName(cardName);
        cardEntity.setConsiderInTotalBalance(considerInTotalBalance);
        cardEntity.setMonitoring(monitoring);

        return cardRepository.save(cardEntity);
    }

    public void deleteCard(UUID cardId) {
        CardEntity card = cardRepository.findById(cardId)
                .orElseThrow(() -> new RecordNotFoundException(MessageUtil.CARD_NOT_FOUND_ERROR));

        UserEntity user = userService.getCurrentUser();
        user.getCards().remove(card);
        userRepository.save(user);
    }
}