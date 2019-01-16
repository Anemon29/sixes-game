package pl.edu.agh.sixes.engine.validation.chain;

import pl.edu.agh.sixes.engine.validation.validator.CardOnFinalPositionValidator;
import pl.edu.agh.sixes.engine.validation.validator.MoveToFinalPositionValidator;
import pl.edu.agh.sixes.model.Board;
import pl.edu.agh.sixes.model.CardContainer;

public class MoveToFinalPositionValidationChain {
    private final MoveToFinalPositionValidator moveValidator;
    private final CardOnFinalPositionValidator cardValidator;

    public MoveToFinalPositionValidationChain() {
        this.moveValidator = new MoveToFinalPositionValidator();
        this.cardValidator = new CardOnFinalPositionValidator();
    }

    public boolean validate(Board board, CardContainer first, CardContainer second){
        if (cardValidator.validate(board, first)){
            return false;
        }
        return moveValidator.validate(board, first, second);
    }

}
