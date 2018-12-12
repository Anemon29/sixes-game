package pl.edu.agh.sixes.model.stack;

import pl.edu.agh.sixes.model.Card;
import pl.edu.agh.sixes.model.CardContainer;

import java.util.List;

public class Deck extends CardsStack {

    public Deck(List<Card> cards) {
        super(cards, CardContainer.Place.Deck);
    }

}
