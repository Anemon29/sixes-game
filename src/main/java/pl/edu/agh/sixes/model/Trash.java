package pl.edu.agh.sixes.model;

public class Trash extends CardsStack {

    public Trash() {
        super();
    }

    @Override
    public Card pop() {
        throw new UnsupportedOperationException("Popping from trash is forbidden.");
    }

}
