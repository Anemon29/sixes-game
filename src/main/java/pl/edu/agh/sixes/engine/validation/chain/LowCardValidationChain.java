package pl.edu.agh.sixes.engine.validation.chain;

import pl.edu.agh.sixes.model.CardContainer;

public class LowCardValidationChain {
    private final DefaultValidationChain defaultValidationChain;

    public LowCardValidationChain() {
        this.defaultValidationChain = new DefaultValidationChain();
    }

    public boolean validate(CardContainer first, CardContainer second){
        if (defaultValidationChain.validate(first, second)){
            switch (first.getContent().get().getRank()) {
                case TWO: return true;
                case THREE: return true;
                case FOUR: return true;
                case FIVE: return true;
                case ACE: return true;
                default: return false;
            }
        }
        return false;
    }
}
