package pl.edu.agh.sixes.model.stack;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.edu.agh.sixes.model.Card;
import pl.edu.agh.sixes.model.CardContainer;

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

    protected CardsStack(CardContainer.Place place) {
        this.cards = FXCollections.observableArrayList();
        this.numberOfCards = 0;
        CardContainer container = new CardContainer(place, 0, 0);
        this.container = new SimpleObjectProperty<>(container);
    }

    public void push(Card card){
        this.cards.add(numberOfCards, card);
        numberOfCards++;
        setContainerContent(card);
    }

    public Card peek() {
        if (numberOfCards < 1) {
            throw new IndexOutOfBoundsException("Can't peek/pop from empty stack.");
        }
        return this.cards.get(numberOfCards - 1);
    }

    public Card pop() {
        numberOfCards--;
        setContainerContent(peek());
        return this.cards.remove(numberOfCards);
    }

    public boolean isEmpty() {
        return numberOfCards < 1;
    }

    public List<Card> getCards() {
        return cards;
    }

    public ObservableList<Card> getObservableCards() {
        return cards;
    }

    public CardContainer getContainer() {
        return container.getValue();
    }

    public ObjectProperty<CardContainer> getContainerProperty() {
        return container;
    }

    private void setContainerContent(Card card) {
        getContainer().setContent(card);
    }
}
