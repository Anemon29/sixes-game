package pl.edu.agh.sixes.engine.generator;

import com.google.common.collect.Lists;
import pl.edu.agh.sixes.model.Card;
import pl.edu.agh.sixes.model.stack.Deck;

import java.util.Collections;
import java.util.List;

public class DeckGenerator {

    public Deck initializeDeck(){
        List<Card> cards = Lists.newLinkedList();
        for (int i = 0; i < 2; i++) {
            generateAllCards(cards);
        }
        Collections.shuffle(cards);
        return new Deck(cards);
    }

    private void generateAllCards(List<Card> cards) {
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()){
                cards.add(new Card(rank, suit));
            }
        }
    }
}
