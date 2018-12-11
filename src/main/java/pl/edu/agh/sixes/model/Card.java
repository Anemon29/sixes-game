package pl.edu.agh.sixes.model;


public class Card {

    private Rank rank;
    private Suit suit;

    private static final String[] RANK_CODES = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
    private static final String[] SUIT_CODES = {"C", "D", "H", "S"};

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return rank == card.rank &&
                suit == card.suit;
    }
    @Override
    public String toString(){
        return  RANK_CODES[this.rank.ordinal()] + SUIT_CODES[this.suit.ordinal()];
    }
}
