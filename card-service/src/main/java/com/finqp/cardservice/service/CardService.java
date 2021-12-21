package com.finqp.cardservice.service;

import com.finqp.cardservice.model.Banking;
import com.finqp.cardservice.model.Card;

import java.util.List;

public interface CardService {
    List<Card> getAll();
    Card getCardById(long id) throws IllegalArgumentException;
    Card getCardByBanking(String banking) throws IllegalArgumentException;
    String createCard(String banking, String number);
    void updateCard(long id, String banking, String number)
            throws IllegalArgumentException;
    void deleteCard(long id);
}