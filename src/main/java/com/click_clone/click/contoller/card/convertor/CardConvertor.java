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
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Mapper(componentModel = "spring")
public abstract class CardConvertor {
    @Autowired
    private UserService userService;
    @Autowired
    private AttachmentRepository attachmentRepository;

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

    @Mapping(target = "bankImageId", expression = "java(card.getBankImage().getId())")
    @Mapping(target = "balance", source = "balance", qualifiedByName = "convertBalance")
    @Mapping(target = "currencyType", expression = "java(card.getCardType().getName())")
    public abstract CardResponseDto cardToDto(CardEntity card) throws IOException;

    public abstract List<CardResponseDto> cardListToDtoList(List<CardEntity> cardEntityList);

    @Named("convertBalance")
    protected String convertBalance(BigInteger balance) {
        String balanceString = balance.toString();
        int length = balanceString.length();
        return balanceString.substring(0, length - 3) + "," + balanceString.substring(length - 3);
    }

    @Mapping(target = "bankImage", source = "bankName", qualifiedByName = "setBankImage")
    public abstract CardEntity dtoToCard(CardCreateRequestDto request);

    @Named("setBankImage")
    protected AttachmentEntity setBankImage(String bankName) throws IOException {
        Optional<AttachmentEntity> byName = attachmentRepository.findByName(bankName);

        if (byName.isEmpty()) {
            return AttachmentUtil
                    .buildAttachmentFromDefaultImage(DEFAULT_IMAGE_URL + bankName + ".png");
        }

        return byName.get();
    }

    @AfterMapping
    protected void addUserToEntity(CardEntity card) throws IOException {
        card.setUser(userService.getCurrentUser());
        setCardType(card);
        setCardTypeImage(card);
    }

    protected void setCardType(CardEntity card) {
        String substring = card.getCardNumber().substring(0, 4);
        card.setCardType(cardTypeMap.get(substring));
        card.setCurrencyType(currencyTypeMap.get(card.getCardType()));
    }

    protected void setCardTypeImage(CardEntity card) throws IOException {
        String cardTypeName = card.getCardType().toString();
        Optional<AttachmentEntity> byName = attachmentRepository.findByName(cardTypeName);

        AttachmentEntity attachment;
        if (byName.isEmpty()) {
             attachment = AttachmentUtil
                    .buildAttachmentFromDefaultImage(DEFAULT_IMAGE_URL + cardTypeName + ".png");
        } else {
            attachment = byName.get();
        }

        card.setCardTypeImage(attachment);
    }
}
