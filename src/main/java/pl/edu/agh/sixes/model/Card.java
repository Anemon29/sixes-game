package pl.edu.agh.sixes.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Card {

    private Rank rank;
    private Suit suit;

    public enum Rank {
        TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE
    }

    public enum Suit {
        CLUBS, DIAMONDS, HEARTS, SPADES
    }

}
