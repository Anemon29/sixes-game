package pl.edu.agh.sixes.engine.generator;

import com.google.common.collect.Lists;
import pl.edu.agh.sixes.model.Card;
import pl.edu.agh.sixes.model.CardContainer;
import pl.edu.agh.sixes.model.stack.Deck;
import pl.edu.agh.sixes.model.Row;

import java.util.List;

public class RowsGenerator {

    public List<Row> initializeRows(Deck deck){
        List<Row> rowList = Lists.newLinkedList();
        List<Card> deckCards = deck.getCards();
        for (int i = 0; i < 4; i++) {
            List<CardContainer> row = Lists.newLinkedList();
            for (int j = 0; j < 8; j++) {
                Card top = deckCards.remove(deckCards.size() - 1);
                CardContainer container = new CardContainer(CardContainer.Place.Field, i, j, top);
                row.add(container);
            }
            rowList.add(new Row(row));
        }
        return rowList;
    }
}
