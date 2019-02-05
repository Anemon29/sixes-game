package pl.edu.agh.sixes.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.List;
import java.util.Optional;


public class Row {

    private List<CardContainer> cardsRow;

    private ObjectProperty<Card.Suit> suit;

    public Row(List<CardContainer> cardsRow) {
        this.cardsRow = cardsRow;
        this.suit = new SimpleObjectProperty<>(null);
    }

    public Optional<Card.Suit> getSuit() {
        Card.Suit s = suit.get();
        return Optional.ofNullable(s);
    }

    public ObjectProperty<Card.Suit> suitProperty() {
        return suit;
    }

    public List<CardContainer> getCardsRow() {
        return cardsRow;
    }

    public boolean bindSuit(Card card){
        if (suit.get() == null){
            this.suit.set(card.getSuit());
            return true;
        }
        return false;
    }

    public void unbindSuit(){
        if (suit.get() != null){
            this.suit.set(null);
        }
    }

}
