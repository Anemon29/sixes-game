package pl.edu.agh.sixes.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.Optional;


public class Row {

    private ObservableList<CardContainer> cardsRow;

    private Optional<Card.Suit> suit;

    public Row(List<CardContainer> cardsRow) {
        this.cardsRow = FXCollections.observableArrayList(cardsRow);
        this.suit = Optional.empty();
    }

    public Optional<Card.Suit> getSuit() {
        return suit;
    }

    public List<CardContainer> getCardsRow() {
        return cardsRow;
    }

    public ObservableList<CardContainer> getObservableCardsRow() {
        return cardsRow;
    }

    public void bindSuit(Card card){
        if (suit.isPresent()){
            throw new IllegalStateException("Suit already binded");
        }
        this.suit = Optional.of(card.getSuit());
    }

}
