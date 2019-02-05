package pl.edu.agh.sixes.engine.validation.chain;

import pl.edu.agh.sixes.engine.validation.validator.NoFreePlaceValidator;
import pl.edu.agh.sixes.model.Board;

public class NewRoundValidationChain {

    private final NoFreePlaceValidator noFreePlaceValidator;

    public NewRoundValidationChain() {
        this.noFreePlaceValidator = new NoFreePlaceValidator();
    }

    public boolean validate(Board board) {
        return noFreePlaceValidator.validate(board);
    }
}
