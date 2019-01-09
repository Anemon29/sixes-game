package pl.edu.agh.sixes.command.builder;

import pl.edu.agh.sixes.command.Command;
import pl.edu.agh.sixes.command.MoveToFinalPositionCommand;
import pl.edu.agh.sixes.command.PutFromHandOnBoardCommand;
import pl.edu.agh.sixes.command.PutHighCardIntoTrashCommand;
import pl.edu.agh.sixes.command.PutIntoRejectedCommand;
import pl.edu.agh.sixes.command.PutLowCardsIntoTrashCommand;
import pl.edu.agh.sixes.command.TakeFromRejectedCommand;
import pl.edu.agh.sixes.model.Board;
import pl.edu.agh.sixes.model.CardContainer;

public class CommandBuilder {

    private Board board;
    private CardContainer first;
    private CardContainer second;
    private ClickedConfiguration configuration;

    public CommandBuilder(Board board, CardContainer first, CardContainer second) {
        this.board = board;
        this.first = first;
        this.second = second;
    }

    public Command build() {

        configuration = new ClickedConfiguration.Builder().withFirstContainer(first).withSecondContainer(second).build();

        switch (configuration.getFirstPlace()) {
            case DECK:
                return handleFirstDeck();
            case REJECTED:
                return handleFirstRejected();
            case FIELD:
                return handleFirstField();
            case TRASH:
                throw new IllegalArgumentException("Critical error. You can't click TRASH.");
            default:
                throw new IllegalArgumentException("Unrecognised place clicked: " + first.getPlace());
        }
    }

    private Command handleFirstDeck() {
        switch (configuration.getSecondPlace()) {
            case DECK:
                throw new IllegalArgumentException("Critical error. You can't click DECK and DECK.");
            case REJECTED:
                return handleDeckThenRejected();
            case FIELD:
                return handleDeckThenField();
            case TRASH:
                throw new IllegalArgumentException("Critical error. You can't click TRASH.");
            default:
                throw new IllegalArgumentException("Unrecognised place clicked: " + second.getPlace());
        }
    }

    private Command handleFirstRejected() {
        switch (configuration.getSecondPlace()) {
            case DECK:
                return handleRejectedThenDeck();
            case REJECTED:
            throw new IllegalArgumentException("Critical error. You can't click DECK and DECK.");
            case FIELD:
                return handleRejectedThenField();
            case TRASH:
                throw new IllegalArgumentException("Critical error. You can't click TRASH.");
            default:
                throw new IllegalArgumentException("Unrecognised place clicked: " + second.getPlace());
        }
    }

    private Command handleFirstField() {
        switch (configuration.getSecondPlace()) {
            case DECK:
                return handleFieldThenDeck();
            case REJECTED:
                return handleFieldThenRejected();
            case FIELD:
                return handleFieldThenField();
            case TRASH:
                throw new IllegalArgumentException("Critical error. You can't click TRASH.");
            default:
                throw new IllegalArgumentException("Unrecognised place clicked: " + second.getPlace());
        }
    }

    private Command handleDeckThenRejected() {
        if (configuration.getFirstRank().equals(ClickedConfiguration.Rank.EMPTY)) {
            throw new IllegalArgumentException("DECK can't be empty.");
        }
        if (configuration.getCards().equals(ClickedConfiguration.Cards.DIFFERENT)) {
            return new PutIntoRejectedCommand(board);
        } else {
            return handleSameCards();
        }
    }

    private Command handleDeckThenField() {
        if (configuration.getFirstRank().equals(ClickedConfiguration.Rank.EMPTY)) {
            throw new IllegalArgumentException("DECK can't be empty.");
        }
        if (configuration.getSecondRank().equals(ClickedConfiguration.Rank.EMPTY)) {
            return new PutFromHandOnBoardCommand(board, first, second);
        }
        if (configuration.getCards().equals(ClickedConfiguration.Cards.DIFFERENT)) {
            throw new IllegalArgumentException("You can't click DECK and FIELD with different cards.");
        } else {
            return handleSameCards();
        }
    }

    private Command handleRejectedThenDeck() {
        if (configuration.getCards().equals(ClickedConfiguration.Cards.DIFFERENT)) {
            throw new IllegalArgumentException("You can't click REJECTED and DECK with different cards.");
        } else {
            return handleSameCards();
        }
    }

    private Command handleRejectedThenField() {
        if (configuration.getFirstRank().equals(ClickedConfiguration.Rank.EMPTY)) {
            throw new IllegalArgumentException("REJECTED can't be empty while clicking first on REJECTED.");
        }
        if (configuration.getSecondRank().equals(ClickedConfiguration.Rank.EMPTY)) {
            return new TakeFromRejectedCommand(board, first, second);
        }
        if (configuration.getCards().equals(ClickedConfiguration.Cards.DIFFERENT)) {
            throw new IllegalArgumentException("You can't click DECK and FIELD with different cards.");
        } else {
            return handleSameCards();
        }
    }

    private Command handleFieldThenDeck() {
        if (configuration.getCards().equals(ClickedConfiguration.Cards.DIFFERENT)) {
            throw new IllegalArgumentException("You can't click FIELD and DECK with different cards.");
        } else {
            return handleSameCards();
        }
    }

    private Command handleFieldThenRejected() {
        if (configuration.getFirstRank().equals(ClickedConfiguration.Rank.EMPTY)) {
            throw new IllegalArgumentException("First FIELD can't be empty.");
        }
        if (configuration.getCards().equals(ClickedConfiguration.Cards.DIFFERENT)) {
            throw new IllegalArgumentException("You can't click FIELD and REJECTED with different cards.");
        } else {
            return handleSameCards();
        }
    }

    private Command handleFieldThenField() {
        if (configuration.getFirstRank().equals(ClickedConfiguration.Rank.EMPTY)) {
            throw new IllegalArgumentException("First FIELD can't be empty.");
        }
        if (configuration.getSecondRank().equals(ClickedConfiguration.Rank.EMPTY)) {
            return new MoveToFinalPositionCommand(board, first, second);
        }
        if (configuration.getCards().equals(ClickedConfiguration.Cards.DIFFERENT)) {
            throw new IllegalArgumentException("You can't click FIELD and FIELD with different cards.");
        } else {
            return handleSameCards();
        }
    }

    private Command handleSameCards() {
        if (configuration.getFirstRank().equals(ClickedConfiguration.Rank.LOW)) {
            return new PutLowCardsIntoTrashCommand(board, first, second);
        } else {
            return new PutHighCardIntoTrashCommand(board, second);
        }
    }

}
