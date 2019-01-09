package pl.edu.agh.sixes.engine.validation.validator;

import pl.edu.agh.sixes.model.Board;
import pl.edu.agh.sixes.model.CardContainer;
import pl.edu.agh.sixes.model.Row;


public class NoFreePlaceValidator {

    public boolean validate(Board board) {
        if (!board.getRejectedCards().isEmpty()) {
            for (Row row : board.getRows()) {
                for (CardContainer cardContainer : row.getCardsRow()) {
                    if (!cardContainer.getContent().isPresent()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
