package pl.edu.agh.sixes.model;

import java.util.List;

public class ActiveCardRows {

    private List<Row> rows;

    public ActiveCardRows(List<Row> rows) {
        this.rows = rows;
    }

    public ActiveCardRows() {
    }

    public List<Row> getRows() {
        return rows;
    }
}
