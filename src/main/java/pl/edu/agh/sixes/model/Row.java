package pl.edu.agh.sixes.model;

import java.util.List;
import java.util.Optional;


public class Row {

    private List<Card> cardsRow;

    private Optional<Card.Suit> suit;

    public Row(List<Card> cardsRow) {
        this.cardsRow = cardsRow;
        this.suit = Optional.empty();
    }

    public Optional<Card.Suit> getSuit() {
        return suit;
    }

    public List<Card> getCardsRow() {
        return cardsRow;
    }

    public void setSuit(Optional<Card.Suit> suit) {
        this.suit = suit;
    }

    public void bindSuit(Card card){
        if (suit.isPresent()){
            throw new IllegalStateException("Suit already binded");
        }
        setSuit(Optional.of(card.getSuit()));
    }

    public void move(Card card, Integer index){
        cardsRow.add(index, card);
    }

}
