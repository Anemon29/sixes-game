package pl.edu.agh.sixes.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Board {

    private ActiveCardRows rows;
    private Deck deck;
    private RejectedCards rejectedCards;
    private Trash trash;

    public Board(ActiveCardRows rows, Deck deck, RejectedCards rejectedCards, Trash trash) {
        this.rows = rows;
        this.deck = deck;
        this.rejectedCards = rejectedCards;
        this.trash = trash;
    }

    public Board(Deck deck) {
        this.deck = deck;
    }

    public ActiveCardRows getRows() {
        return rows;
    }

    public Deck getDeck() {
        return deck;
    }

    public RejectedCards getRejectedCards() {
        return rejectedCards;
    }

    public Trash getTrash() {
        return trash;
    }


}
