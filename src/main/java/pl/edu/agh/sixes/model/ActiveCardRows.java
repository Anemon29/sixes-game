package pl.edu.agh.sixes.model;

import javafx.collections.ObservableList;

public class ActiveCardRows {

    private ObservableList<Row> rows;

    public ActiveCardRows(ObservableList<Row> rows) {
        this.rows = rows;
    }

    public ActiveCardRows() {
    }

    public ObservableList<Row> getRows() {
        return rows;
    }
}
