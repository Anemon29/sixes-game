package pl.edu.agh.sixes.model.stack;

import pl.edu.agh.sixes.model.CardContainer;

public class RejectedCards extends CardsStack {

    public RejectedCards() {
        super(CardContainer.Place.Rejected);
    }

}
