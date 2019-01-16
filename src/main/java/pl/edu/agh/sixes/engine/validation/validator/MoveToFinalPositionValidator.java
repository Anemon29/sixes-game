package pl.edu.agh.sixes.engine.validation.validator;

import pl.edu.agh.sixes.model.Board;
import pl.edu.agh.sixes.model.Card;
import pl.edu.agh.sixes.model.CardContainer;
import pl.edu.agh.sixes.model.Row;

public class MoveToFinalPositionValidator {

    public boolean validate(Board board, CardContainer first, CardContainer second){
        int colId = second.getCoordinates().get().getColumnId();
        int rowId = second.getCoordinates().get().getRowId();
        Row target = board.getRows().get(rowId);
        Card.Rank rank = Card.Rank.convertColumnId(colId);

        if (first.getContent().get().getRank() == rank){
            if (target.getSuit().isPresent()){
                return target.getSuit().get().equals(first.getContent().get().getSuit());
            }
            return true;
        }
        return false;
    }

}
