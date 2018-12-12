package pl.edu.agh.sixes.model;

import java.util.Optional;

public class CardContainer {

    private final Place place;
    private final Coordinates coordinates;
    private Optional<Card> content;

    public CardContainer(Place place, int rowId, int columnId, Card card) {
        this.place = place;
        this.coordinates = place.getCoordinates(rowId, columnId);
        this.content = Optional.of(card);
    }

    public CardContainer(Place place, int rowId, int columnId) {
        this.place = place;
        this.coordinates = place.getCoordinates(rowId, columnId);
        this.content = Optional.empty();
    }

    public Place getPlace() {
        return place;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Optional<Card> getContent() {
        return content;
    }

    public void setContent(Card card) {
        this.content = Optional.ofNullable(card);
    }

    public enum Place {
        Deck {
            @Override
            public Coordinates getCoordinates(int rowId, int columnId) {
                return null;
            }
        },
        Rejected {
            @Override
            public Coordinates getCoordinates(int rowId, int columnId) {
                return null;
            }
        },
        Trash {
            @Override
            public Coordinates getCoordinates(int rowId, int columnId) {
                return null;
            }
        },
        Field {
            @Override
            public Coordinates getCoordinates(int rowId, int columnId) {
                return new Coordinates(rowId, columnId);
            }
        };

        public abstract Coordinates getCoordinates(int rowId, int columnId);
    }

    public static class Coordinates {

        private final int rowId;
        private final int columnId;

        public Coordinates(int rowId, int columnId) {
            if (rowId < 0 || rowId > 3) {
                throw new IllegalArgumentException("RowId value must be one of {0,1,2,3}. Actual value: " + rowId);
            }
            if (columnId < 0 || columnId > 7) {
                throw new IllegalArgumentException("ColumnId value must be one of {0,1,2,3,4,5,6,7}. Actual value: " + columnId);
            }
            this.rowId = rowId;
            this.columnId = columnId;
        }

        public int getRowId() {
            return rowId;
        }

        public int getColumnId() {
            return columnId;
        }

    }

}
