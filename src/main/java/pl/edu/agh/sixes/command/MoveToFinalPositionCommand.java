package pl.edu.agh.sixes.command;

import pl.edu.agh.sixes.model.Board;
import pl.edu.agh.sixes.model.Card;
import pl.edu.agh.sixes.model.Coordinates;

public class MoveToFinalPositionCommand implements Command {

    private final Board board;
    private final Card card;
    private final Coordinates cardCoordinates;

    public MoveToFinalPositionCommand(Board board, Card card, Coordinates cardCoordinates) {
        this.board = board;
        this.card = card;
        this.cardCoordinates = cardCoordinates;
    }

    @Override
    public void execute() {

    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }

    public Board getBoard() {
        return board;
    }

    public Card getCard() {
        return card;
    }

    public Coordinates getCardCoordinates() {
        return cardCoordinates;
    }

}
