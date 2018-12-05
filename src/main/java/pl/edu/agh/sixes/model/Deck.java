package pl.edu.agh.sixes.model;


import java.util.List;

public class Deck extends CardsStack {

    public Deck(List<Card> cards) {
        super(cards);
    }

    public Deck() {
        super();
    }

    @Override
    public void push(Card card) {
        throw new UnsupportedOperationException("Putting card into deck is forbidden.");
    }

}
