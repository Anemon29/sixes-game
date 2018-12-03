package pl.edu.agh.sixes.model;

public class RejectedCards extends CardsContainer {

    public Card takeLast(){
        return super.getCards().getLast();
    }

    public RejectedCards() {
        super();
    }
}
