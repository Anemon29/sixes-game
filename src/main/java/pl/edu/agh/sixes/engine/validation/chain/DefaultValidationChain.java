package pl.edu.agh.sixes.engine.validation.chain;

import pl.edu.agh.sixes.model.CardContainer;

public class DefaultValidationChain {

    public boolean validate(CardContainer first, CardContainer second){
        if (first.getContent().get().equals(second.getContent().get())) {
            if (first.getCoordinates().get().equals(second.getCoordinates().get())){
                throw new IllegalArgumentException("You can't choose the same card twice");
            }
            return true;
        }
        return false;
    }
}
