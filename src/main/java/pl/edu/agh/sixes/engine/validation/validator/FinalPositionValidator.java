package pl.edu.agh.sixes.engine.validation.validator;

import pl.edu.agh.sixes.model.Board;
import pl.edu.agh.sixes.model.Card;
import pl.edu.agh.sixes.model.CardContainer;
import pl.edu.agh.sixes.model.Row;

public class FinalPositionValidator {

    private boolean validate(Board board, CardContainer first, CardContainer second){
        int colId = second.getCoordinates().get().getColumnId();
        int rowId = second.getCoordinates().get().getRowId();
        Row target = board.getRows().get(rowId);
        Card.Rank rank = convertColumnId(colId);

        if (first.getContent().get().getRank() == rank){
            if (target.getSuit().isPresent()){
                return target.getSuit().get().equals(first.getContent().get().getSuit());
            }
            return true;
        }
        return false;
    }

    private Card.Rank convertColumnId(int id){
        switch (id) {
            case 0: return Card.Rank.SIX;
            case 1: return Card.Rank.SEVEN;
            case 2: return Card.Rank.EIGHT;
            case 3: return Card.Rank.NINE;
            case 4: return Card.Rank.TEN;
            case 5: return Card.Rank.JACK;
            case 6: return Card.Rank.QUEEN;
            case 7: return Card.Rank.KING;
            default: return null;
        }
    }

}
