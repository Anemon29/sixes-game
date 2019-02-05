package pl.edu.agh.sixes.command.builder;

import pl.edu.agh.sixes.command.Command;
import pl.edu.agh.sixes.command.PutHighCardIntoTrashCommand;
import pl.edu.agh.sixes.model.Board;
import pl.edu.agh.sixes.model.CardContainer;

public class CommandBuilder {

    private Board board;
    private CardContainer first;

    public CommandBuilder(Board board, CardContainer first) {
        this.board = board;
        this.first = first;
    }

    public Command build() {
        return new PutHighCardIntoTrashCommand(board, first);
    }

}
