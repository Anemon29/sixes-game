package pl.edu.agh.sixes.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Board {

    private ActiveCardRows rows;
    private Deck deck;
    private RejectedCards rejectedCards;
    private Trash trash;



}
