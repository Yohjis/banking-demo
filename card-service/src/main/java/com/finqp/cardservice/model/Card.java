package com.finqp.cardservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "cards")
final public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    private String number;
    private String  banking;

    public Card() {}

    public Long getId() {
        return id;
    }

    public Card(String banking, String number) {
        this.banking = banking;
        this.number = number;
    }

    public String getBanking() {
        return banking;
    }

    public void setBanking(String banking) {
        this.banking = banking;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}