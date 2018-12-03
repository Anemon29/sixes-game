package pl.edu.agh.sixes.engine.generator;

import pl.edu.agh.sixes.model.*;

public class InitialStageGenerator {

    private DeckGenerator deckGenerator;
    private RowsGenerator rowsGenerator;

    public InitialStageGenerator(DeckGenerator deckGenerator, RowsGenerator rowsGenerator) {
        this.deckGenerator = deckGenerator;
        this.rowsGenerator = rowsGenerator;
    }

    public Board initializeBoard(){
        Deck deck = deckGenerator.initializeDeck();
        ActiveCardRows rows = rowsGenerator.initializeRows(deck);
        RejectedCards rejectedCards = new RejectedCards();
        Trash trash = new Trash();
        Board board = new Board(rows, deck, rejectedCards, trash);

        return board;
    }

}
