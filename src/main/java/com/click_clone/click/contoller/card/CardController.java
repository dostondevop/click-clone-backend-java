package com.click_clone.click.contoller.card;

import com.click_clone.click.contoller.card.convertor.CardConvertor;
import com.click_clone.click.contoller.card.dto.CardCreateRequestDto;
import com.click_clone.click.contoller.card.dto.CardDeleteRequestDto;
import com.click_clone.click.contoller.card.dto.CardResponseDto;
import com.click_clone.click.contoller.card.dto.CardUpdateRequestDto;
import com.click_clone.click.contoller.convertor.AttachmentConvertor;
import com.click_clone.click.entity.AttachmentEntity;
import com.click_clone.click.entity.CardEntity;
import com.click_clone.click.repository.AttachmentRepository;
import com.click_clone.click.service.CardService;
import com.click_clone.click.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/card")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;
    private final CardConvertor cardConvertor;

    @GetMapping
    public List<CardResponseDto> getUserCards() {
        List<CardEntity> cardList = cardService.getCardList();
        return cardConvertor.cardListToDtoList(cardList);
    }

    @GetMapping("/{cardId}")
    public CardResponseDto getUserCard(@PathVariable UUID cardId) {
        CardEntity card = cardService.getCard(cardId);
        return cardConvertor.cardToDto(card);
    }

    @GetMapping("/main")
    public CardResponseDto getUserMainCard() {
        CardEntity mainCard = cardService.getMainCard();
        return cardConvertor.cardToDto(mainCard);
    }

    @GetMapping("/balance")
    public String getTotalBalance() {
        return cardService.getTotalBalance();
    }

    @PostMapping
    public CardResponseDto createCard(@RequestBody CardCreateRequestDto request) throws IOException {
        CardEntity cardEntity = cardConvertor.dtoToCard(request);
        CardEntity card = cardService.create(cardEntity);
        return cardConvertor.cardToDto(card);
    }

//    @PutMapping("/image")
//    public CardResponseDto addImageToCard(@RequestParam("cardId") UUID id,
//                               @RequestParam("file")MultipartFile file) throws IOException {
//        AttachmentEntity attachment = AttachmentConvertor.convertToEntity(file);
//        CardEntity card = cardService.addImageTo(id, attachment);
//        return cardConvertor.cardToDto(card);
//    }

    @PutMapping
    public CardResponseDto updateCard(@RequestBody CardUpdateRequestDto request) {
        CardEntity card = cardService.updateCard(request.getId(), request.getCardName(),
                request.isConsiderInTotalBalance(),
                request.isMonitoring());
        return cardConvertor.cardToDto(card);
    }

    @DeleteMapping
    public void deleteCard(@RequestBody CardDeleteRequestDto request) {
        cardService.deleteCard(request.getCardId());
    }

//    @GetMapping("/currency")
//    public Double getCurrency() {
//        return currencyService.getExchangeRate();
//    }
}