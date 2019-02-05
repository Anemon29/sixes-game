package pl.edu.agh.sixes.engine.generator;

import com.google.common.collect.Lists;
import pl.edu.agh.sixes.model.Card;
import pl.edu.agh.sixes.model.CardContainer;
import pl.edu.agh.sixes.model.CardsStack;

import java.util.Collections;
import java.util.List;

public class DeckGenerator {

    public CardsStack initializeDeck(){
        List<Card> cards = Lists.newLinkedList();
        for (int i = 0; i < 2; i++) {
            generateAllCards(cards);
        }
        Collections.shuffle(cards);
        return new CardsStack(cards, CardContainer.Place.DECK);
    }

    private void generateAllCards(List<Card> cards) {
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()){
                cards.add(new Card(rank, suit));
            }
        }
    }
}
