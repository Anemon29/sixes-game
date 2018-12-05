package pl.edu.agh.sixes.engine.generator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.edu.agh.sixes.model.ActiveCardRows;
import pl.edu.agh.sixes.model.Deck;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class InitialStageGeneratorTest {

    private InitialStageGenerator initialStageGenerator;
    @Mock
    private DeckGenerator deckGenerator;
    @Mock
    private RowsGenerator rowsGenerator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        initialStageGenerator = new InitialStageGenerator(deckGenerator, rowsGenerator);
    }

    @Test
    void initializeBoard() {
        //given
        Deck deck = new Deck();
        when(deckGenerator.initializeDeck()).thenReturn(deck);
        when(rowsGenerator.initializeRows(deck)).thenReturn(new ActiveCardRows());

        //when
        initialStageGenerator.initializeBoard();

        //then
        verify(deckGenerator, times(1)).initializeDeck();
        verify(rowsGenerator, times(1)).initializeRows(deck);

    }
}