package pl.edu.agh.sixes.command.builder;

import javafx.util.Pair;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.edu.agh.sixes.command.Command;
import pl.edu.agh.sixes.command.MoveToFinalPositionCommand;
import pl.edu.agh.sixes.command.PutFromHandOnBoardCommand;
import pl.edu.agh.sixes.command.PutHighCardIntoTrashCommand;
import pl.edu.agh.sixes.command.PutIntoRejectedCommand;
import pl.edu.agh.sixes.command.PutLowCardsIntoTrashCommand;
import pl.edu.agh.sixes.command.TakeFromRejectedCommand;
import pl.edu.agh.sixes.engine.generator.DeckGenerator;
import pl.edu.agh.sixes.engine.generator.InitialStageGenerator;
import pl.edu.agh.sixes.engine.generator.RowsGenerator;
import pl.edu.agh.sixes.model.Board;
import pl.edu.agh.sixes.model.Card;
import pl.edu.agh.sixes.model.CardContainer;
import pl.edu.agh.sixes.model.Coordinates;

import java.util.Objects;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.fail;

class CommandBuilderTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @ParameterizedTest
    @MethodSource("argumentsProvider")
    void build(CardContainer.Place firstPlace, Rank firstRank, CardContainer.Place secondPlace, Rank secondRank, Cards cards, Class expectedCommand) {
        //given
        Board board = initializeBoard();
        Pair<CardContainer, CardContainer> containers = new Builder()
                .withFirstPlace(firstPlace)
                .withFirstRank(firstRank)
                .withSecondPlace(secondPlace)
                .withSecondRank(secondRank)
                .withCards(cards)
                .build();
        CommandBuilder commandBuilder = new CommandBuilder(board, containers.getKey(), containers.getValue());
        Command command = null;
        try {
            //when
            command = commandBuilder.build();

            //then
        } catch (Exception e) {
            if (!expectedCommand.equals(Exception.class)) {
                fail(e);
            }
        }

        if (Objects.nonNull(command)) {
            if (!expectedCommand.equals(command.getClass())) {
                fail("Error while building. Builded: " + command.getClass().toString() + ", expected: " + expectedCommand.toString());
            }
        } else {
            if (!expectedCommand.equals(Exception.class)) {
                fail("Something went wrong. Nothing builded, expected: " + expectedCommand.toString());
            }
        }

    }

    private static Stream<Arguments> argumentsProvider() {
        return Stream.of(
                Arguments.of(CardContainer.Place.DECK, Rank.HIGH, CardContainer.Place.DECK, Rank.HIGH, Cards.SAME, Exception.class),
                Arguments.of(CardContainer.Place.DECK, Rank.HIGH, CardContainer.Place.REJECTED, Rank.HIGH, Cards.DIFFERENT, PutIntoRejectedCommand.class),
                Arguments.of(CardContainer.Place.DECK, Rank.LOW, CardContainer.Place.REJECTED, Rank.LOW, Cards.DIFFERENT, PutIntoRejectedCommand.class),
                Arguments.of(CardContainer.Place.DECK, Rank.HIGH, CardContainer.Place.REJECTED, Rank.HIGH, Cards.SAME, PutHighCardIntoTrashCommand.class),
                Arguments.of(CardContainer.Place.DECK, Rank.LOW, CardContainer.Place.REJECTED, Rank.LOW, Cards.SAME, PutLowCardsIntoTrashCommand.class),
                Arguments.of(CardContainer.Place.DECK, Rank.HIGH, CardContainer.Place.FIELD, Rank.HIGH, Cards.SAME, PutHighCardIntoTrashCommand.class),
                Arguments.of(CardContainer.Place.DECK, Rank.HIGH, CardContainer.Place.FIELD, Rank.HIGH, Cards.DIFFERENT, Exception.class),
                Arguments.of(CardContainer.Place.DECK, Rank.LOW, CardContainer.Place.FIELD, Rank.LOW, Cards.SAME, PutLowCardsIntoTrashCommand.class),
                Arguments.of(CardContainer.Place.DECK, Rank.LOW, CardContainer.Place.FIELD, Rank.LOW, Cards.DIFFERENT, Exception.class),
                Arguments.of(CardContainer.Place.DECK, Rank.HIGH, CardContainer.Place.FIELD, Rank.EMPTY, Cards.DIFFERENT, PutFromHandOnBoardCommand.class),
                Arguments.of(CardContainer.Place.DECK, Rank.LOW, CardContainer.Place.FIELD, Rank.EMPTY, Cards.DIFFERENT, PutFromHandOnBoardCommand.class),
                Arguments.of(CardContainer.Place.REJECTED, Rank.HIGH, CardContainer.Place.DECK, Rank.HIGH, Cards.SAME, PutHighCardIntoTrashCommand.class),
                Arguments.of(CardContainer.Place.REJECTED, Rank.HIGH, CardContainer.Place.DECK, Rank.HIGH, Cards.DIFFERENT, Exception.class),
                Arguments.of(CardContainer.Place.REJECTED, Rank.LOW, CardContainer.Place.DECK, Rank.LOW, Cards.SAME, PutLowCardsIntoTrashCommand.class),
                Arguments.of(CardContainer.Place.REJECTED, Rank.LOW, CardContainer.Place.DECK, Rank.LOW, Cards.DIFFERENT, Exception.class),
                Arguments.of(CardContainer.Place.REJECTED, Rank.HIGH, CardContainer.Place.REJECTED, Rank.HIGH, Cards.DIFFERENT, Exception.class),
                Arguments.of(CardContainer.Place.REJECTED, Rank.HIGH, CardContainer.Place.FIELD, Rank.EMPTY, Cards.DIFFERENT, TakeFromRejectedCommand.class),
                Arguments.of(CardContainer.Place.REJECTED, Rank.HIGH, CardContainer.Place.FIELD, Rank.HIGH, Cards.SAME, PutHighCardIntoTrashCommand.class),
                Arguments.of(CardContainer.Place.REJECTED, Rank.HIGH, CardContainer.Place.FIELD, Rank.HIGH, Cards.DIFFERENT, Exception.class),
                Arguments.of(CardContainer.Place.REJECTED, Rank.LOW, CardContainer.Place.FIELD, Rank.LOW, Cards.SAME, PutLowCardsIntoTrashCommand.class),
                Arguments.of(CardContainer.Place.REJECTED, Rank.LOW, CardContainer.Place.FIELD, Rank.LOW, Cards.DIFFERENT, Exception.class),
                Arguments.of(CardContainer.Place.REJECTED, Rank.HIGH, CardContainer.Place.FIELD, Rank.LOW, Cards.DIFFERENT, Exception.class),
                Arguments.of(CardContainer.Place.FIELD, Rank.HIGH, CardContainer.Place.DECK, Rank.HIGH, Cards.SAME, PutHighCardIntoTrashCommand.class),
                Arguments.of(CardContainer.Place.FIELD, Rank.HIGH, CardContainer.Place.DECK, Rank.HIGH, Cards.DIFFERENT, Exception.class),
                Arguments.of(CardContainer.Place.FIELD, Rank.LOW, CardContainer.Place.DECK, Rank.LOW, Cards.SAME, PutLowCardsIntoTrashCommand.class),
                Arguments.of(CardContainer.Place.FIELD, Rank.LOW, CardContainer.Place.DECK, Rank.LOW, Cards.DIFFERENT, Exception.class),
                Arguments.of(CardContainer.Place.FIELD, Rank.HIGH, CardContainer.Place.REJECTED, Rank.HIGH, Cards.SAME, PutHighCardIntoTrashCommand.class),
                Arguments.of(CardContainer.Place.FIELD, Rank.HIGH, CardContainer.Place.REJECTED, Rank.HIGH, Cards.DIFFERENT, Exception.class),
                Arguments.of(CardContainer.Place.FIELD, Rank.LOW, CardContainer.Place.REJECTED, Rank.LOW, Cards.SAME, PutLowCardsIntoTrashCommand.class),
                Arguments.of(CardContainer.Place.FIELD, Rank.LOW, CardContainer.Place.REJECTED, Rank.LOW, Cards.DIFFERENT, Exception.class),
                Arguments.of(CardContainer.Place.FIELD, Rank.EMPTY, CardContainer.Place.REJECTED, Rank.LOW, Cards.DIFFERENT, Exception.class),
                Arguments.of(CardContainer.Place.FIELD, Rank.HIGH, CardContainer.Place.FIELD, Rank.HIGH, Cards.SAME, PutHighCardIntoTrashCommand.class),
                Arguments.of(CardContainer.Place.FIELD, Rank.HIGH, CardContainer.Place.FIELD, Rank.HIGH, Cards.DIFFERENT, Exception.class),
                Arguments.of(CardContainer.Place.FIELD, Rank.LOW, CardContainer.Place.FIELD, Rank.LOW, Cards.SAME, PutLowCardsIntoTrashCommand.class),
                Arguments.of(CardContainer.Place.FIELD, Rank.LOW, CardContainer.Place.FIELD, Rank.LOW, Cards.DIFFERENT, Exception.class),
                Arguments.of(CardContainer.Place.FIELD, Rank.EMPTY, CardContainer.Place.FIELD, Rank.LOW, Cards.DIFFERENT, Exception.class),
                Arguments.of(CardContainer.Place.FIELD, Rank.HIGH, CardContainer.Place.FIELD, Rank.EMPTY, Cards.DIFFERENT, MoveToFinalPositionCommand.class)
        );
    }

    private enum Rank {
        HIGH(Card.Rank.KING),
        LOW(Card.Rank.TWO),
        EMPTY(null);

        private final Card card;

        Rank(Card.Rank rank) {
            if (Objects.nonNull(rank)) {
                this.card = new Card(rank, Card.Suit.HEARTS);
            } else {
                this.card = null;
            }
        }

        public Card getCard() {
            return card;
        }
    }

    private enum Cards {
        SAME,
        DIFFERENT
    }

    private Board initializeBoard() {
        return new InitialStageGenerator(new DeckGenerator(), new RowsGenerator()).initializeBoard();
    }

    private static class Builder {
        private CardContainer.Place firstPlace;
        private Rank firstRank;
        private CardContainer.Place secondPlace;
        private Rank secondRank;
        private Cards cards;

        Builder() {
        }

        Builder withFirstPlace(CardContainer.Place firstPlace) {
            this.firstPlace = firstPlace;
            return this;
        }

        Builder withFirstRank(Rank firstRank) {
            this.firstRank = firstRank;
            return this;
        }

        Builder withSecondPlace(CardContainer.Place secondPlace) {
            this.secondPlace = secondPlace;
            return this;
        }

        Builder withSecondRank(Rank secondRank) {
            this.secondRank = secondRank;
            return this;
        }

        Builder withCards(Cards cards) {
            this.cards = cards;
            return this;
        }

        Pair<CardContainer, CardContainer> build() {
            Card firstCard = firstRank.getCard();
            Card secondCard = secondRank.getCard();
            if (cards.equals(Cards.DIFFERENT) && !firstRank.equals(Rank.EMPTY)) {
                if (firstRank.equals(Rank.LOW)) {
                    firstCard = new Card(Card.Rank.THREE, Card.Suit.HEARTS);
                } else {
                    firstCard = new Card(Card.Rank.QUEEN, Card.Suit.HEARTS);
                }
            }

            CardContainer first = new CardContainer(firstPlace, new Coordinates(0, 0), firstCard);
            CardContainer second = new CardContainer(secondPlace, new Coordinates(0, 0), secondCard);
            return new Pair<>(first, second);
        }

    }

}