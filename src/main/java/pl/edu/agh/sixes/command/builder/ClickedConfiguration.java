package pl.edu.agh.sixes.command.builder;

import pl.edu.agh.sixes.model.Card;
import pl.edu.agh.sixes.model.CardContainer;

import java.util.Objects;

public class ClickedConfiguration {
    private final CardContainer.Place firstPlace;
    private final Rank firstRank;
    private final CardContainer.Place secondPlace;
    private final Rank secondRank;
    private final Cards cards;

    ClickedConfiguration(CardContainer.Place firstPlace, Rank firstRank, CardContainer.Place secondPlace, Rank secondRank, Cards cards) {
        this.firstPlace = firstPlace;
        this.firstRank = firstRank;
        this.secondPlace = secondPlace;
        this.secondRank = secondRank;
        this.cards = cards;
    }

    public enum Rank {
        HIGH,
        LOW,
        EMPTY
    }

    public enum Cards {
        SAME,
        DIFFERENT
    }

    public CardContainer.Place getFirstPlace() {
        return firstPlace;
    }

    public Rank getFirstRank() {
        return firstRank;
    }

    public CardContainer.Place getSecondPlace() {
        return secondPlace;
    }

    public Rank getSecondRank() {
        return secondRank;
    }

    public Cards getCards() {
        return cards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClickedConfiguration)) return false;
        ClickedConfiguration that = (ClickedConfiguration) o;
        return firstPlace == that.firstPlace &&
                firstRank == that.firstRank &&
                secondPlace == that.secondPlace &&
                secondRank == that.secondRank &&
                cards == that.cards;
    }

    @Override
    public int hashCode() {

        return Objects.hash(firstPlace, firstRank, secondPlace, secondRank, cards);
    }

    public static class Builder {
        private CardContainer first;
        private CardContainer second;

        Builder() {
        }

        Builder withFirstContainer(CardContainer first) {
            this.first = first;
            return this;
        }

        Builder withSecondContainer(CardContainer second) {
            this.second = second;
            return this;
        }

        ClickedConfiguration build() {
            CardContainer.Place firstPlace = first.getPlace();
            Rank firstRank = getRank(first);
            CardContainer.Place secondPlace = second.getPlace();
            Rank secondRank = getRank(second);
            Cards cards = getCards(first, second);
            return new ClickedConfiguration(firstPlace, firstRank, secondPlace, secondRank, cards);
        }

        private Rank getRank(CardContainer container) {
            if (container.getContent().isPresent()) {
                Card.Rank rank = container.getContent().get().getRank();
                if (rank.isLow()) {
                    return Rank.LOW;
                } else {
                    return Rank.HIGH;
                }
            } else {
                return Rank.EMPTY;
            }
        }

        private Cards getCards(CardContainer first, CardContainer second) {
            if (!first.getContent().isPresent() || !second.getContent().isPresent()) {
                return Cards.DIFFERENT;
            } else {
                Card firstCard = first.getContent().get();
                Card secondCard = second.getContent().get();
                if (firstCard.equals(secondCard)) {
                    return Cards.SAME;
                } else {
                    return Cards.DIFFERENT;
                }
            }
        }

    }

}
