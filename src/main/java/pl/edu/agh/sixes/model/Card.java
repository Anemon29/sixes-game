package pl.edu.agh.sixes.model;


import java.util.Objects;

public class Card {

    private final Rank rank;
    private final Suit suit;

    private static final String[] RANK_CODES = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
    private static final String[] SUIT_CODES = {"C", "D", "H", "S"};

    public enum Rank {
        TWO(true), THREE(true), FOUR(true), FIVE(true),
        SIX(false), SEVEN(false), EIGHT(false), NINE(false), TEN(false), JACK(false), QUEEN(false), KING(false),
        ACE(true);

        private final boolean isLow;

        Rank(boolean isLow) {
            this.isLow = isLow;
        }

        public boolean isLow() {
            return isLow;
        }
    }

    public enum Suit {
        CLUBS, DIAMONDS, HEARTS, SPADES
    }

    public Card(Rank rank, Suit suit) {
        Objects.requireNonNull(rank, "Card.Rank cannot be null.");
        Objects.requireNonNull(suit, "Card.Suit cannot be null.");
        this.rank = rank;
        this.suit = suit;
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public boolean isLow() {
        return rank.isLow;
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
