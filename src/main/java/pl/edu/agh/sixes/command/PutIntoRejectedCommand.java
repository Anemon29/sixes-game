package pl.edu.agh.sixes.command;

import pl.edu.agh.sixes.model.Board;

public class PutIntoRejectedCommand implements Command {
    private final Board board;

    public PutIntoRejectedCommand(Board board) {
        this.board = board;
    }

    @Override
    public void execute() {
        board.getRejectedCards().push(board.getDeck().pop());
    }

    @Override
    public void undo() {
        board.getDeck().push(board.getRejectedCards().pop());
    }

    @Override
    public void redo() {
        execute();
    }
}
