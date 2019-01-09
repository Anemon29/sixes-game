package pl.edu.agh.sixes.engine.validation.validator;

import pl.edu.agh.sixes.model.Board;
import pl.edu.agh.sixes.model.Card;
import pl.edu.agh.sixes.model.CardContainer;
import pl.edu.agh.sixes.model.Row;

public class CardOnFinalPositionValidator {

    public boolean validate(Board board, CardContainer first){
        int firstColId = first.getCoordinates().get().getColumnId();
        int firstRowId = first.getCoordinates().get().getRowId();
        if (first.getContent().get().getRank().equals(Card.Rank.convertColumnId(firstColId))){
            Row row = board.getRows().get(firstRowId);
            if (row.getSuit().isPresent()) {
                return first.getContent().get().getSuit().equals(row.getSuit().get());
            }
        }
        return false;
    }
}
