package pl.edu.agh.sixes.engine.validation.chain;

import pl.edu.agh.sixes.model.CardContainer;

public interface ValidationChain {

    boolean validate(CardContainer first, CardContainer second);

}
