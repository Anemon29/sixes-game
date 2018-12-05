package pl.edu.agh.sixes.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Board {

    private ObjectProperty<ActiveCardRows> rows;
    private ObjectProperty<Deck> deck;
    private ObjectProperty<RejectedCards> rejectedCards;
    private ObjectProperty<Trash> trash;

    public Board(ActiveCardRows rows, Deck deck, RejectedCards rejectedCards, Trash trash) {
        this.rows = new SimpleObjectProperty<>(rows);
        this.deck = new SimpleObjectProperty<>(deck);
        this.rejectedCards = new SimpleObjectProperty<>(rejectedCards);
        this.trash = new SimpleObjectProperty<>(trash);
    }

    public Board(Deck deck) {
        this.deck = new SimpleObjectProperty<>(deck);
    }

    public ActiveCardRows getRows() {
        return rows.get();
    }

    public Deck getDeck() {
        return deck.get();
    }

    public RejectedCards getRejectedCards() {
        return rejectedCards.get();
    }

    public Trash getTrash() {
        return trash.get();
    }

    public ObjectProperty<ActiveCardRows> getRowsProperty() {
        return rows;
    }

    public ObjectProperty<Deck> getDeckProperty() {
        return deck;
    }

    public ObjectProperty<RejectedCards> getRejectedCardsProperty() {
        return rejectedCards;
    }

    public ObjectProperty<Trash> getTrashProperty() {
        return trash;
    }
}
