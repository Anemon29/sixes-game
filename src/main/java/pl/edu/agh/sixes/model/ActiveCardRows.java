package pl.edu.agh.sixes.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ActiveCardRows {

    private List<Row> rows;

    public ActiveCardRows(List<Row> rows) {
        this.rows = rows;
    }
}
