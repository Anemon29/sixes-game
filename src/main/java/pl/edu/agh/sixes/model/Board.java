package pl.edu.agh.sixes.model;

import java.util.List;

public class Board {

    private List<Row> rows;
    private CardsStack deck;
    private CardsStack rejectedCards;
    private CardsStack trash;

    public Board(List<Row> rows, CardsStack deck, CardsStack rejectedCards, CardsStack trash) {
        this.rows = rows;
        this.deck = deck;
        this.rejectedCards = rejectedCards;
        this.trash = trash;
    }

    public List<Row> getRows() {
        return rows;
    }

    public CardsStack getDeck() {
        return deck;
    }

    public CardsStack getRejectedCards() {
        return rejectedCards;
    }

    public CardsStack getTrash() {
        return trash;
    }
}
