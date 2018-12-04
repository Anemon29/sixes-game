package pl.edu.agh.sixes.engine.generator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.edu.agh.sixes.model.ActiveCardRows;
import pl.edu.agh.sixes.model.Card;
import pl.edu.agh.sixes.model.Deck;
import pl.edu.agh.sixes.model.Row;

public class RowsGenerator {

    public ActiveCardRows initializeRows(Deck deck){
        ObservableList<Row> rowList = FXCollections.observableArrayList();
        ObservableList<Card> deckCards = deck.getCards();
        for (int i = 0; i < 4; i++) {
            ObservableList<Card> row = FXCollections.observableArrayList();
            for (int j = 0; j < 8; j++) {
                Card top = deckCards.remove(deckCards.size() - 1);
                row.add(top);
            }
            rowList.add(new Row(row));
        }
        return new ActiveCardRows(rowList);
    }
}
