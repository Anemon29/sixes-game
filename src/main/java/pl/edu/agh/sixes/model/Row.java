package pl.edu.agh.sixes.model;

import java.util.List;
import java.util.Optional;


public class Row {

    private List<CardContainer> cardsRow;

    private Card.Suit suit;

    public Row(List<CardContainer> cardsRow) {
        this.cardsRow = cardsRow;
        this.suit = null;
    }

    public Optional<Card.Suit> getSuit() {
        return Optional.ofNullable(suit);
    }

    public List<CardContainer> getCardsRow() {
        return cardsRow;
    }

    public void bindSuit(Card card){
        if (suit != null){
            throw new IllegalStateException("Suit already binded");
        }
        this.suit = card.getSuit();
    }

}
