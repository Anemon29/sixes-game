package pl.edu.agh.sixes.engine.generator;

import com.google.common.collect.Lists;
import pl.edu.agh.sixes.model.Card;
import pl.edu.agh.sixes.model.CardContainer;
import pl.edu.agh.sixes.model.Coordinates;
import pl.edu.agh.sixes.model.CardsStack;
import pl.edu.agh.sixes.model.Row;

import java.util.List;

public class RowsGenerator {

    private static final int NUM_OF_ROWS = 4;
    private static final int CARDS_IN_ROW = 8;

    public List<Row> initializeRows(CardsStack deck){
        List<Row> rowList = Lists.newLinkedList();
        List<Card> deckCards = deck.getCards();
        for (int i = 0; i < NUM_OF_ROWS; i++) {
            List<CardContainer> row = Lists.newLinkedList();
            for (int j = 0; j < CARDS_IN_ROW; j++) {
                Card top = deckCards.remove(deckCards.size() - 1);
                CardContainer container = new CardContainer(CardContainer.Place.FIELD, new Coordinates(i, j), top);
                row.add(container);
            }
            rowList.add(new Row(row));
        }
        return rowList;
    }
}
