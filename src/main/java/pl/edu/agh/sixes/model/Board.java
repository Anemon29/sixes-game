package pl.edu.agh.sixes.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import pl.edu.agh.sixes.model.stack.Deck;
import pl.edu.agh.sixes.model.stack.RejectedCards;
import pl.edu.agh.sixes.model.stack.Trash;

import java.util.Collections;
import java.util.List;

public class Board {

    private List<Row> rows;
    private ObjectProperty<Deck> deck;
    private ObjectProperty<RejectedCards> rejectedCards;
    private ObjectProperty<Trash> trash;

    public Board(List<Row> rows, Deck deck, RejectedCards rejectedCards, Trash trash) {
        this.rows = rows;
        this.deck = new SimpleObjectProperty<>(deck);
        this.rejectedCards = new SimpleObjectProperty<>(rejectedCards);
        this.trash = new SimpleObjectProperty<>(trash);
    }

    public List<Row> getRows() {
        return Collections.unmodifiableList(rows);
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
