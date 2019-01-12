package pl.edu.agh.sixes.command;

import pl.edu.agh.sixes.engine.validation.chain.MoveToFinalPositionValidationChain;
import pl.edu.agh.sixes.model.Board;
import pl.edu.agh.sixes.model.CardContainer;

public class MoveToFinalPositionCommand implements Command {

    private final Board board;
    private final CardContainer first;
    private final CardContainer second;
    private final MoveToFinalPositionValidationChain validationChain;
    private boolean isBindingMove;

    public MoveToFinalPositionCommand(Board board, CardContainer first, CardContainer second) {
        this.board = board;
        this.first = first;
        this.second = second;
        this.validationChain = new MoveToFinalPositionValidationChain();
        this.isBindingMove = false;
    }

    @Override
    public void execute() {
        if (second.getContent().isPresent()) {
            throw new IllegalStateException("Final position is not empty.");
        }
        if (first.getContent().isPresent()) {
            if (validationChain.validate(board, first, second)) {
                second.setContent(first.getContent().get());
                first.setContent(null);
                isBindingMove = board.getRows().get(second.getCoordinates().get().getRowId()).bindSuit(second.getContent().get());
            }
            else {
                throw new IllegalArgumentException("Field binded to another rank or another suit");
            }
        } else {
            throw new IllegalStateException("Move empty container to final position.");
        }
    }

    @Override
    public void undo() {
        if (first.getContent().isPresent()) {
            throw new IllegalStateException("Critical error. Undo final position not empty.");
        }
        if (second.getContent().isPresent()) {
            first.setContent(second.getContent().get());
            second.setContent(null);
            if (isBindingMove) {
                board.getRows().get(second.getCoordinates().get().getRowId()).unbindSuit();
            }
        } else {
            throw new IllegalStateException("Move empty container to final position.");
        }
    }

    @Override
    public void redo() {
        execute();
    }

}
