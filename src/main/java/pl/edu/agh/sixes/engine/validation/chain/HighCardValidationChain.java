package pl.edu.agh.sixes.engine.validation.chain;

import pl.edu.agh.sixes.engine.validation.validator.CardOnFinalPositionValidator;
import pl.edu.agh.sixes.model.Board;
import pl.edu.agh.sixes.model.CardContainer;

public class HighCardValidationChain {

    private final CardOnFinalPositionValidator validator;

    public enum Result {
        FIRST,
        SECOND,
        NONE
    }

    public HighCardValidationChain() {
        this.validator = new CardOnFinalPositionValidator();
    }

    public Result validate(Board board, CardContainer first, CardContainer second) {
        if (validator.validate(board, first))
            return Result.FIRST;
        else if (validator.validate(board, second))
            return Result.SECOND;
        else return Result.NONE;
    }
}
