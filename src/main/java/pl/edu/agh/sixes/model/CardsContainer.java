package pl.edu.agh.sixes.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public abstract class CardsContainer {

    private ObservableList<Card> cards;

    public CardsContainer(ObservableList<Card> cards) {
        this.cards = cards;
    }

    public CardsContainer() {
        this.cards = FXCollections.observableArrayList();
    }

    public ObservableList<Card> getCards() {
        return cards;
    }

    public void addCard(Card card){
        this.cards.add(card);
    }
}
