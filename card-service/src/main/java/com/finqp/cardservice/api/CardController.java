package com.finqp.cardservice.api;


import com.finqp.cardservice.model.Banking;
import com.finqp.cardservice.model.Card;
import com.finqp.cardservice.model.dto.CardDto;
import com.finqp.cardservice.service.impl.CardServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/cards")
@RestController
public class CardController {
    private final CardServiceImpl cardService;

    @GetMapping("/all")
    public ResponseEntity<List<Card>> getAll() {
        final List<Card> cards = cardService.getAll();
        return ResponseEntity.ok(cards);
    }

    @GetMapping("/{banking}")
    public ResponseEntity<Card> getByBanking(@PathVariable String banking) {
        try {
            Card card = cardService.getCardByBanking(banking);
            return  ResponseEntity.ok(card);
        }catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }

    }

   /* @GetMapping("/{banking}/{number}")
    public ResponseEntity<Card> getByBankingAndNumber(@PathVariable String number, @PathVariable String banking) {
        try {

            Card cardByBanking = cardService.getCardByBanking(banking);
            Card cardByNumber = cardService.getCardByBanking(banking).getNumber(number);

            return ResponseEntity.ok(card);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
*/
    @GetMapping("/dto/{id}")
    public ResponseEntity<CardDto> getDtoById(@PathVariable long id) {
        try {
            Card card = cardService.getCardById(id);
            CardDto cardDto = new CardDto(card.getBanking(), card.getNumber());

            return ResponseEntity.ok(cardDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody CardDto cardDto) {
        final String banking = cardDto.getBanking();
        final String number = cardDto.getNumber();

        final String cardId = cardService.createCard(banking, number);
        final String cardUri = "/Card" + cardId;

        return ResponseEntity.created(URI.create(cardUri)).build();
    }

   /* @PatchMapping("/{number}")
    public ResponseEntity<Void> change(@PathVariable  String number, @RequestBody CardDto cardDto) {
        final Bunking bunking = cardDto.getBunking();
        final String number = cardDto.getNumber();

        try {
            cardService.updateCard(bunking, number);

            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }*/

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        cardService.deleteCard(id);

        return ResponseEntity.noContent().build();
    }
}