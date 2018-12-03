package pl.edu.agh.sixes.engine.generator;

import pl.edu.agh.sixes.model.ActiveCardRows;
import pl.edu.agh.sixes.model.Card;
import pl.edu.agh.sixes.model.Deck;
import pl.edu.agh.sixes.model.Row;

import java.util.ArrayList;
import java.util.List;

public class RowsGenerator {

    public ActiveCardRows initializeRows(Deck deck){
        List<Row> rowList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            List<Card> row = new ArrayList<>();
            for (int j = 0; j < 8; j++) {
                Card top = deck.getCards().getLast();
                deck.getCards().removeLast();
                row.add(top);
            }
            rowList.add(new Row(row));
        }
        return new ActiveCardRows(rowList);
    }
}
