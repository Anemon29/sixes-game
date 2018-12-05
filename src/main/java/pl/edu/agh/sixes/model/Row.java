package pl.edu.agh.sixes.model;

import javafx.collections.ObservableList;

import java.util.Optional;


public class Row {

    private ObservableList<Card> cardsRow;

    private Optional<Card.Suit> suit;

    public Row(ObservableList<Card> cardsRow) {
        this.cardsRow = cardsRow;
        this.suit = Optional.empty();
    }

    public Optional<Card.Suit> getSuit() {
        return suit;
    }

    public ObservableList<Card> getCardsRow() {
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

    public void moveCardIntoRowIndex(Card card, Integer index){
        cardsRow.add(index, card);
    }

}
