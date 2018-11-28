package pl.edu.agh.sixes.model;

import lombok.Getter;

import java.util.LinkedList;

@Getter
public abstract class CardsContainer {

    private LinkedList<Card> cards;

    public void addCard(Card card){
        cards.add(card);
    }

}
