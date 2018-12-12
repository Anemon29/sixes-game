package pl.edu.agh.sixes.model.stack;

import pl.edu.agh.sixes.model.Card;
import pl.edu.agh.sixes.model.CardContainer;

public class Trash extends CardsStack {

    public Trash() {
        super(null);
    }

    @Override
    public Card pop() {
        throw new UnsupportedOperationException("Popping from trash is forbidden.");
    }

}
