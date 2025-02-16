package com.click_clone.click.contoller.card.convertor;

import com.click_clone.click.contoller.card.dto.CardCreateRequestDto;
import com.click_clone.click.contoller.card.dto.CardResponseDto;
import com.click_clone.click.entity.AttachmentEntity;
import com.click_clone.click.entity.CardEntity;
import com.click_clone.click.entity.enums.CardType;
import com.click_clone.click.entity.enums.CurrencyType;
import com.click_clone.click.repository.AttachmentRepository;
import com.click_clone.click.service.UserService;
import com.click_clone.click.service.util.AttachmentUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CardConvertor {
    private final UserService userService;
    private final AttachmentRepository attachmentRepository;

    private static final String DEFAULT_IMAGE_URL = "/static/image/";

    private static final Map<String, CardType> cardTypeMap = Map.of(
            "8600", CardType.UZCARD,
            "5614", CardType.UZCARD,
            "6262", CardType.UZCARD,
            "9860", CardType.HUMO,
            "4000", CardType.VISA,
            "4111", CardType.VISA,
            "4213", CardType.VISA,
            "4500", CardType.VISA,
            "4278", CardType.VISA
    );

    private static final Map<CardType, CurrencyType> currencyTypeMap = Map.of(
            CardType.UZCARD, CurrencyType.UZS,
            CardType.HUMO, CurrencyType.UZS,
            CardType.VISA, CurrencyType.USD
    );

    public CardResponseDto cardToDto(CardEntity card) {
        return CardResponseDto.builder()
                .id(card.getId())
                .cardNumber(card.getCardNumber())
                .cardName(card.getCardName())
                .expiryDate(card.getExpiryDate())
                .bankImageId(card.getBankImage().getId())
                .balance(card.getBalance().toString())
                .main(card.isMain())
                .currencyType(card.getCurrencyType().toString())
                .considerInTotalBalance(card.isConsiderInTotalBalance())
                .monitoring(card.isMonitoring())
                .build();
    }

    public List<CardResponseDto> cardListToDtoList(List<CardEntity> cardEntityList) {
        return cardEntityList.stream().map(this::cardToDto).collect(Collectors.toList());
    }

    public CardEntity dtoToCard(CardCreateRequestDto request) throws IOException {
        CardEntity card = CardEntity.builder()
                .cardNumber(request.getCardNumber())
                .expiryDate(request.getExpiryDate())
                .bankImage(setBankImage(request.getBankName()))
                .cardName(request.getCardName())
                .main(request.isMain())
                .build();

        addUserToEntity(card);
        return card;
    }

    protected AttachmentEntity setBankImage(String bankName) throws IOException {
        Optional<AttachmentEntity> byName = attachmentRepository.findByName(bankName);

        if (byName.isEmpty()) {
            AttachmentEntity attachment = AttachmentUtil
                    .buildAttachmentFromDefaultImage(DEFAULT_IMAGE_URL + bankName + ".png");
            return attachmentRepository.save(attachment);
        }

        return byName.get();
    }

    private void addUserToEntity(CardEntity card) {
        card.setUser(userService.getCurrentUser());
        setCardType(card);
        setCardTypeImage(card);
    }

    private void setCardType(CardEntity card) {
        String substring = card.getCardNumber().substring(0, 4);
        card.setCardType(cardTypeMap.get(substring));
        card.setCurrencyType(currencyTypeMap.get(card.getCardType()));
    }

    private void setCardTypeImage(CardEntity card) {
        String cardTypeName = card.getCardType().toString();
        AttachmentEntity attachment = attachmentRepository.findByName(cardTypeName)
                .orElseGet(() -> {
                    try {
                        AttachmentEntity attachment1 = AttachmentUtil.buildAttachmentFromDefaultImage(DEFAULT_IMAGE_URL + cardTypeName + ".png");
                        return attachmentRepository.save(attachment1);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
        card.setCardTypeImage(attachment);
    }
}
