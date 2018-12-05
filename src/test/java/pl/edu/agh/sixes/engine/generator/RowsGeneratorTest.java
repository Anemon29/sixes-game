package pl.edu.agh.sixes.engine.generator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.edu.agh.sixes.model.ActiveCardRows;
import pl.edu.agh.sixes.model.Card;
import pl.edu.agh.sixes.model.Deck;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RowsGeneratorTest {

    private static RowsGenerator rowsGenerator;
    @Mock
    private Deck deck;

    @BeforeEach
    void setUp() {
        rowsGenerator = new RowsGenerator();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void initializeRowsTest() {
        //given
        ObservableList<Card> cards = FXCollections.observableArrayList();
        for (int i = 0; i < 104; i++) {
            cards.add(new Card());
        }

        when(deck.getCards()).thenReturn(cards);

        //when
        ActiveCardRows rows = rowsGenerator.initializeRows(deck);

        //then
        verify(deck, times(1)).getCards();
        assertEquals(4, rows.getRows().size());
        assertEquals(8, rows.getRows().get(2).getCardsRow().size());
        assertEquals(104 - 32, deck.getCards().size());
    }
}