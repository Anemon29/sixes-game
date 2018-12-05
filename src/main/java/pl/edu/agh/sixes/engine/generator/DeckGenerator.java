package pl.edu.agh.sixes.engine.generator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.edu.agh.sixes.model.Card;
import pl.edu.agh.sixes.model.Deck;

import java.util.Collections;

public class DeckGenerator {

    public Deck initializeDeck(){
        ObservableList<Card> cards = FXCollections.observableArrayList();
        for (int i = 0; i < 2; i++) {
            for (Card.Suit suit : Card.Suit.values()) {
                for (Card.Rank rank : Card.Rank.values()){
                    cards.add(new Card(rank, suit));
                }
            }
        }
        Collections.shuffle(cards);
        return new Deck(cards);
    }


}
