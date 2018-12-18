package pl.edu.agh.sixes.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.Optional;


public class Row {

    private ObservableList<CardContainer> cardsRow;

    private Card.Suit suit;

    public Row(List<CardContainer> cardsRow) {
        this.cardsRow = FXCollections.observableArrayList(cardsRow);
        this.suit = null;
    }

    public Optional<Card.Suit> getSuit() {
        return Optional.ofNullable(suit);
    }

    public List<CardContainer> getCardsRow() {
        return cardsRow;
    }

    public ObservableList<CardContainer> getObservableCardsRow() {
        return cardsRow;
    }

    public void bindSuit(Card card){
        if (suit != null){
            throw new IllegalStateException("Suit already binded");
        }
        this.suit = card.getSuit();
    }

}
