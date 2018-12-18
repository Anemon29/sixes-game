package pl.edu.agh.sixes.command.builder;

import pl.edu.agh.sixes.command.Command;
import pl.edu.agh.sixes.command.PutHighCardIntoTrashCommand;
import pl.edu.agh.sixes.command.PutLowCardsIntoTrashCommand;
import pl.edu.agh.sixes.model.Board;
import pl.edu.agh.sixes.model.CardContainer;

public class CommandBuilder {

    private Board board;
    private CardContainer first;
    private CardContainer second;

    public CommandBuilder(Board board, CardContainer first) {
        this.board = board;
        this.first = first;
    }

    public CommandBuilder(Board board, CardContainer first, CardContainer second) {
        this(board, first);
        this.second = second;
    }

    public Command build() {
        return new PutHighCardIntoTrashCommand(board, first);
    }

    public Command build2() {return new PutLowCardsIntoTrashCommand(board, first, second);}

}
