package pl.edu.agh.sixes.command;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import pl.edu.agh.sixes.engine.generator.DeckGenerator;
import pl.edu.agh.sixes.engine.generator.InitialStageGenerator;
import pl.edu.agh.sixes.engine.generator.RowsGenerator;
import pl.edu.agh.sixes.model.Board;
import pl.edu.agh.sixes.model.Card;
import pl.edu.agh.sixes.model.CardContainer;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PutHighCardIntoTrashCommandTest {

    private static Board board;
    private PutHighCardIntoTrashCommand command;

    @BeforeAll
    static void init() {
        board = new InitialStageGenerator(new DeckGenerator(), new RowsGenerator()).initializeBoard();
    }

    @Nested
    @DisplayName("from deck")
    class FromDeck {
        private CardContainer container;

        @BeforeEach
        void setUp() {
            board = new InitialStageGenerator(new DeckGenerator(), new RowsGenerator()).initializeBoard();
            container = board.getDeck().getContainer();
        }

        @Test
        void executeUndoRedo() {
            command = new PutHighCardIntoTrashCommand(board, container);
            //execute
            //given
            Card card = board.getDeck().peek();

            //when
            command.execute();

            //then
            assertEquals(card, board.getTrash().peek());
            assertNotSame(card, board.getDeck().peek());

            //undo
            //given
            card = board.getTrash().peek();

            //when
            command.undo();

            //then
            assertEquals(card, board.getDeck().peek());
            assertTrue(board.getTrash().isEmpty());

            //redo
            //given
            card = board.getDeck().peek();

            //when
            command.redo();

            //then
            assertEquals(card, board.getTrash().peek());
            assertNotSame(card, board.getDeck().peek());
            assertFalse(board.getTrash().isEmpty());
        }

    }

    @Nested
    @DisplayName("from field")
    class FromField {
        private CardContainer container;

        @BeforeEach
        void setUp() {
            board = new InitialStageGenerator(new DeckGenerator(), new RowsGenerator()).initializeBoard();
            container = board.getRows().get(0).getCardsRow().get(0);
        }

        @Test
        @SuppressWarnings("all")
        void executeUndoRedo() {
            command = new PutHighCardIntoTrashCommand(board, container);
            //execute
            //given
            Card card = container.getContent().get();

            //when
            command.execute();

            //then
            assertEquals(card, board.getTrash().peek());
            assertEquals(Optional.empty(), container.getContent());

            //undo
            //given
            card = board.getTrash().peek();

            //when
            command.undo();

            //then
            assertEquals(card, container.getContent().get());
            assertTrue(board.getTrash().isEmpty());

            //redo
            //given
            card = container.getContent().get();

            //when
            command.redo();

            //then
            assertEquals(card, board.getTrash().peek());
            assertEquals(Optional.empty(), container.getContent());
            assertFalse(board.getTrash().isEmpty());
        }

    }
}