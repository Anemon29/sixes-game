package pl.edu.agh.sixes.engine.generator;

import pl.edu.agh.sixes.model.Board;
import pl.edu.agh.sixes.model.CardContainer;
import pl.edu.agh.sixes.model.CardsStack;
import pl.edu.agh.sixes.model.Row;

import java.util.List;

public class InitialStageGenerator {

    private DeckGenerator deckGenerator;
    private RowsGenerator rowsGenerator;

    public InitialStageGenerator(DeckGenerator deckGenerator, RowsGenerator rowsGenerator) {
        this.deckGenerator = deckGenerator;
        this.rowsGenerator = rowsGenerator;
    }

    public Board initializeBoard(){
        CardsStack deck = deckGenerator.initializeDeck();
        List<Row> rows = rowsGenerator.initializeRows(deck);
        CardsStack rejectedCards = new CardsStack(CardContainer.Place.REJECTED);
        CardsStack trash = new CardsStack(CardContainer.Place.TRASH);

        return new Board(rows, deck, rejectedCards, trash);
    }

}
