package pl.edu.agh.sixes.model;

public class RejectedCards extends CardsContainer {

    public Card takeLast(){
        return getCards().getLast();
    }

}
