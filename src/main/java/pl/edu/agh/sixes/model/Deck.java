package pl.edu.agh.sixes.model;

import javafx.collections.ObservableList;

public class Deck extends CardsContainer{

    public Deck(ObservableList<Card> cards) {
        super(cards);
    }

    public Deck() {
        super();
    }
}
