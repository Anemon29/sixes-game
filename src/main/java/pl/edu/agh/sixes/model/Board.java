package pl.edu.agh.sixes.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.Collections;
import java.util.List;

public class Board {

    private List<Row> rows;
    private ObjectProperty<CardsStack> deck;
    private ObjectProperty<CardsStack> rejectedCards;
    private ObjectProperty<CardsStack> trash;

    public Board(List<Row> rows, CardsStack deck, CardsStack rejectedCards, CardsStack trash) {
        this.rows = rows;
        this.deck = new SimpleObjectProperty<>(deck);
        this.rejectedCards = new SimpleObjectProperty<>(rejectedCards);
        this.trash = new SimpleObjectProperty<>(trash);
    }

    public List<Row> getRows() {
        return Collections.unmodifiableList(rows);
    }

    public CardsStack getDeck() {
        return deck.get();
    }

    public CardsStack getRejectedCards() {
        return rejectedCards.get();
    }

    public CardsStack getTrash() {
        return trash.get();
    }

    public ObjectProperty<CardsStack> getDeckProperty() {
        return deck;
    }

    public ObjectProperty<CardsStack> getRejectedCardsProperty() {
        return rejectedCards;
    }

    public ObjectProperty<CardsStack> getTrashProperty() {
        return trash;
    }
}
