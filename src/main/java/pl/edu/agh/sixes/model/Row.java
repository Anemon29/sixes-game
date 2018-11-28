package pl.edu.agh.sixes.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class Row {

    private List<Card> cardsRow;

    private Card.Suit suit;

    public Row(List<Card> cardsRow, Card.Suit suit) {
        this.cardsRow = cardsRow;
    }

    public void bindSuit(Card card){
        if (suit == null){
            setSuit(card.getSuit());
            return;
        }
        throw new IllegalStateException("Suit already binded");
    }

    public void move(Card card, Integer index){
        cardsRow.add(index, card);
    }

}
