package com.finqp.cardservice.service.impl;


import com.finqp.cardservice.model.Card;
import com.finqp.cardservice.repository.CardRepository;
import com.finqp.cardservice.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    @Override
    public List<Card> getAll() {
        return cardRepository.findAll();
    }
    @Override
    public Card getCardById(long id) throws IllegalArgumentException {
        final Optional<Card> optionalCard = cardRepository.findById(id);
        if (optionalCard.isPresent())
            return optionalCard.get();
        else
            throw new IllegalArgumentException("Invalid Card ID");
    }

    @Override
    public Card getCardByBanking(String banking) throws IllegalArgumentException {
        final Optional<Card> optionalCard = cardRepository.findByBanking(banking);

        if (optionalCard.isPresent())
            return optionalCard.get();
        else
            throw new IllegalArgumentException("Invalid Card ID");
    }


    @Override
    public String createCard(String banking, String number) {
        final Card card = new Card (banking, number);
        final Card savedCard = cardRepository.save(card);

        return savedCard.getNumber();
    }

    @Override
    public void updateCard(long id, String banking, String number) throws IllegalArgumentException {

        final Optional<Card> optionalCard = cardRepository.findById(id);

        if (optionalCard.isEmpty())
            throw new IllegalArgumentException("Invalid Bus ID");

        final Card card = optionalCard.get();
        if (banking != null) card.setBanking(banking);
        if (number != null && !number.isBlank()) card.setNumber(number);
        cardRepository.save(card);
    }

    @Override
    public void deleteCard(long id) {
        cardRepository.deleteById(id);
    }
}