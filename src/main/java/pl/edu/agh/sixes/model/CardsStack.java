package pl.edu.agh.sixes.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;


public abstract class CardsStack {

    private int numberOfCards;

    private ObservableList<Card> cards;

    private ObjectProperty<CardContainer> container;

    protected CardsStack(List<Card> cards, CardContainer.Place place) {
        this.cards = FXCollections.observableArrayList(cards);
        this.numberOfCards = cards.size();
        CardContainer container;
        if (numberOfCards > 0) {
            container = new CardContainer(place, 0, 0, cards.get(numberOfCards - 1));
        } else {
            container = new CardContainer(place, 0, 0);
        }
        this.container = new SimpleObjectProperty<>(container);
    }

    protected CardsStack(List<Card> cards) {
        this.cards = FXCollections.observableArrayList(cards);
        this.numberOfCards = cards.size();
    }

    protected CardsStack() {
        this.cards = FXCollections.observableArrayList();
        this.numberOfCards = 0;
    }

    public void push(Card card){
        this.cards.add(numberOfCards, card);
        numberOfCards++;
        getContainer().setContent(card);
    }

    public Card peek() {
        if (numberOfCards < 1) {
            throw new IndexOutOfBoundsException("Can't peek/pop from empty stack.");
        }
        return this.cards.get(numberOfCards - 1);
    }

    public Card pop() {
        numberOfCards--;
        getContainer().setContent(peek());
        return this.cards.remove(numberOfCards);
    }

    public List<Card> getCards() {
        return cards;
    }

    public ObservableList<Card> getObservableCards() {
        return cards;
    }

    public CardContainer getContainer() {
        return container.get();
    }

    public ObjectProperty<CardContainer> containerProperty() {
        return container;
    }
}
