package pl.edu.agh.sixes.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.edu.agh.sixes.model.Card;
import pl.edu.agh.sixes.model.CardContainer;

import java.util.List;


public class CardsStack {

    private ObservableList<Card> cards;

    private CardContainer container;

    public CardsStack(List<Card> cards, CardContainer.Place place) {
        this.cards = FXCollections.observableArrayList(cards);
        CardContainer container;
        if (cards.size() > 0) {
            container = new CardContainer(place, null, cards.get(cards.size() - 1));
        } else {
            container = new CardContainer(place, null);
        }
        this.container = container;
    }

    public CardsStack(CardContainer.Place place) {
        this.cards = FXCollections.observableArrayList();
        this.container = new CardContainer(place, null);
    }

    public void push(Card card){
        this.cards.add(cards.size(), card);
        setContainerContent(card);
    }

    public Card peek() {
        if (cards.size() < 1) {
            return null;
        }
        return this.cards.get(cards.size() - 1);
    }

    public Card pop() {
        if (cards.size() < 1) {
            throw new IndexOutOfBoundsException("Can't peek/pop from empty stack.");
        }
        Card removed =  this.cards.remove(cards.size()-1);
        if (cards.size() == 0) {
            setContainerContent(null);
        } else {
            setContainerContent(peek());
        }
        return removed;
    }

    public boolean isEmpty() {
        return cards.size() < 1;
    }

    public List<Card> getCards() {
        return cards;
    }

    public ObservableList<Card> getObservableCards() {
        return cards;
    }

    public CardContainer getContainer() {
        return container;
    }

    private void setContainerContent(Card card) {
        getContainer().setContent(card);
    }
}
