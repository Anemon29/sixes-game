package pl.edu.agh.sixes.model.stack;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;
import pl.edu.agh.sixes.model.Card;
import pl.edu.agh.sixes.model.CardContainer;
import pl.edu.agh.sixes.model.CardsStack;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

class CardsStackTest {

    private class TestCardsStack extends CardsStack {

        TestCardsStack(List<Card> cards) {
            super(cards, CardContainer.Place.DECK);
        }
    }

    @Test
    void pushTest() {
        //given
        TestCardsStack stack = initializeStack();
        Card card = new Card(Card.Rank.ACE, Card.Suit.HEARTS);
        int oldSize = stack.getCards().size();
        Optional<Card> expectedCardFromContainer = Optional.of(card);

        //when
        stack.push(card);
        int newSize = stack.getCards().size();
        Optional<Card> newCardFromContainer = stack.getContainer().getContent();

        //then
        assertEquals(oldSize + 1, newSize);
        assertEquals(expectedCardFromContainer, newCardFromContainer);
    }

    @Test
    void peekTest() {
        //given
        TestCardsStack stack = initializeStack();
        int oldSize = stack.getCards().size();
        Card card = new Card(Card.Rank.FOUR, Card.Suit.HEARTS);
        Optional<Card> cardFromContainer = stack.getContainer().getContent();

        //when
        Card top = stack.peek();
        Card againTop = stack.peek();
        int newSize = stack.getCards().size();
        Optional<Card> newCardFromContainer = stack.getContainer().getContent();

        //then
        assertEquals(card, top);
        assertEquals(card, againTop);
        assertEquals(oldSize, newSize);
        assertEquals(cardFromContainer, newCardFromContainer);
    }

    @Test
    void peekFromEmptyTest() {
        //given
        TestCardsStack emptyStack = new TestCardsStack(Lists.newLinkedList());

        //when
        int numberOfCards = emptyStack.getCards().size();
        Supplier<Card> peekReference = emptyStack::peek;

        //then
        assertEquals(0, numberOfCards);
        assertThrows(IndexOutOfBoundsException.class, peekReference::get);
    }

    @Test
    void popTest() {
        //given
        TestCardsStack stack = initializeStack();
        int oldSize = stack.getCards().size();
        Card properTop = new Card(Card.Rank.FOUR, Card.Suit.HEARTS);
        Optional<Card> expectedCardFromContainer = Optional.of(new Card(Card.Rank.THREE, Card.Suit.HEARTS));

        //when
        Card top = stack.pop();
        int newSize = stack.getCards().size();
        Optional<Card> newCardFromContainer = stack.getContainer().getContent();

        //then
        assertEquals(properTop, top);
        assertEquals(oldSize - 1, newSize);
        assertEquals(expectedCardFromContainer, newCardFromContainer);
    }

    @Test
    void popFromOneElementTest() {
        //given
        TestCardsStack stack = initializeOneElementStack();
        int oldSize = stack.getCards().size();
        Card properTop = new Card(Card.Rank.TWO, Card.Suit.HEARTS);
        Optional<Card> expectedCardFromContainer = Optional.empty();

        //when
        Card top = stack.pop();
        int newSize = stack.getCards().size();
        Optional<Card> newCardFromContainer = stack.getContainer().getContent();

        //then
        assertEquals(properTop, top);
        assertEquals(oldSize - 1, newSize);
        assertEquals(expectedCardFromContainer, newCardFromContainer);
    }

    @Test
    void popFromEmptyTest() {
        //given
        TestCardsStack emptyStack = new TestCardsStack(Lists.newLinkedList());

        //when
        Supplier<Card> popReference = emptyStack::pop;

        //then
        assertThrows(IndexOutOfBoundsException.class, popReference::get);
    }

    @Test
    void isEmptyTest() {
        //given
        TestCardsStack emptyStack = new TestCardsStack(Lists.newLinkedList());
        TestCardsStack notEmptyStack = initializeStack();

        //when
        boolean isEmptyStackEmpty = emptyStack.isEmpty();
        boolean isNotEmptyStackEmpty = notEmptyStack.isEmpty();

        //then
        assertTrue(isEmptyStackEmpty);
        assertFalse(isNotEmptyStackEmpty);
    }

    private TestCardsStack initializeStack() {
        List<Card> cards = Lists.newLinkedList();
        cards.add(new Card(Card.Rank.TWO, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.THREE, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.FOUR, Card.Suit.HEARTS));
        return new TestCardsStack(cards);
    }

    private TestCardsStack initializeOneElementStack() {
        List<Card> cards = Lists.newLinkedList();
        cards.add(new Card(Card.Rank.TWO, Card.Suit.HEARTS));
        return new TestCardsStack(cards);
    }

}