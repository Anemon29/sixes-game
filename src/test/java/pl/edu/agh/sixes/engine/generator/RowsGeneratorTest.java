package pl.edu.agh.sixes.engine.generator;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.edu.agh.sixes.model.Card;
import pl.edu.agh.sixes.model.CardsStack;
import pl.edu.agh.sixes.model.Row;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RowsGeneratorTest {

    private static RowsGenerator rowsGenerator;
    @Mock
    private CardsStack deck;

    @BeforeEach
    void setUp() {
        rowsGenerator = new RowsGenerator();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void initializeRowsTest() {
        //given
        List<Card> cards = Lists.newLinkedList();
        for (int i = 0; i < 104; i++) {
            cards.add(new Card(Card.Rank.ACE, Card.Suit.HEARTS));
        }

        when(deck.getCards()).thenReturn(cards);

        //when
        List<Row> rows = rowsGenerator.initializeRows(deck);

        //then
        verify(deck, times(1)).getCards();
        assertEquals(4, rows.size());
        assertEquals(8, rows.get(2).getCardsRow().size());
        assertEquals(104 - 32, deck.getCards().size());
    }
}