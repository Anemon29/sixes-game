package pl.edu.agh.sixes.model;

import java.util.LinkedList;


public abstract class CardsContainer {

    private LinkedList<Card> cards;

    public void addCard(Card card){
        cards.add(card);
    }

    public CardsContainer() {
        this.cards = new LinkedList<>();
    }

    public CardsContainer(LinkedList<Card> cards) {
        this.cards = cards;
    }

    public LinkedList<Card> getCards() {
        return cards;
    }
}
