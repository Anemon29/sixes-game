package pl.edu.agh.sixes.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;

@Getter
@Setter
@EqualsAndHashCode
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
