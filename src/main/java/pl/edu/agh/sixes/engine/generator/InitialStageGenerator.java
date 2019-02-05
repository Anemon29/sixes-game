package pl.edu.agh.sixes.engine.generator;

import pl.edu.agh.sixes.model.Board;
import pl.edu.agh.sixes.model.stack.Deck;
import pl.edu.agh.sixes.model.stack.RejectedCards;
import pl.edu.agh.sixes.model.Row;
import pl.edu.agh.sixes.model.stack.Trash;

import java.util.List;

public class InitialStageGenerator {

    private DeckGenerator deckGenerator;
    private RowsGenerator rowsGenerator;

    public InitialStageGenerator(DeckGenerator deckGenerator, RowsGenerator rowsGenerator) {
        this.deckGenerator = deckGenerator;
        this.rowsGenerator = rowsGenerator;
    }

    public Board initializeBoard(){
        Deck deck = deckGenerator.initializeDeck();
        List<Row> rows = rowsGenerator.initializeRows(deck);
        RejectedCards rejectedCards = new RejectedCards();
        Trash trash = new Trash();

        return new Board(rows, deck, rejectedCards, trash);
    }

}
