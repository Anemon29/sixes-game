package pl.edu.agh.sixes.model;


public class Card {

    private Rank rank;
    private Suit suit;

    public enum Rank {
        TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE
    }

    public enum Suit {
        CLUBS, DIAMONDS, HEARTS, SPADES
    }

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Card() {
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public Boolean equals(Card other){
        if (this.getSuit().equals(other.getSuit()) && this.getRank().equals(other.getRank()))
            return Boolean.TRUE;
        return Boolean.FALSE;
    }

}
