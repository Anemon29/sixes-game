package pl.edu.agh.sixes.engine.validation.chain;

import pl.edu.agh.sixes.engine.validation.validator.MoveToFinalPositionValidator;
import pl.edu.agh.sixes.model.Board;
import pl.edu.agh.sixes.model.CardContainer;

public class MoveToFinalPositionValidationChain {
    private final MoveToFinalPositionValidator validator;

    public MoveToFinalPositionValidationChain() {
        this.validator = new MoveToFinalPositionValidator();
    }

    public boolean validate(Board board, CardContainer first, CardContainer second){
        return validator.validate(board, first, second);
    }

}
