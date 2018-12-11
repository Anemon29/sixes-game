package pl.edu.agh.sixes.controller;


import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import pl.edu.agh.sixes.command.CommandRegistry;
import pl.edu.agh.sixes.model.ActiveCardRows;
import pl.edu.agh.sixes.model.Board;
import pl.edu.agh.sixes.model.Card;
import pl.edu.agh.sixes.model.Row;

public class BoardController {

    private AppController appController;

    private CommandRegistry commandRegistry;

    @FXML
    private GridPane boardGrid;

    @FXML
    private void initialize() {

        boardGrid.setStyle("-fx-alignment: center");
        for (int i = 0; i < 4; i++) {
            GridPane emptySlots = createRow();
            for (int j = 0; j < 8; j++) {
                emptySlots.add(new Rectangle(80, 122), j, 0);
            }
            boardGrid.add(emptySlots, 1, i);
        }
//        boardGrid.add();

    }

    public void setBoard(Board board) {
        ActiveCardRows activeCardRows = board.getRows();
        ObservableList<Row> rows = activeCardRows.getRows();
        for (int i = 0; i < 4; i++) {
            ObservableList<Card> row = rows.get(i).getCardsRow();
            GridPane gridPane = (GridPane) boardGrid.getChildren().get(i);
            int j = 0;
            for (Card c : row) {
                gridPane.add(createCard(c), j, 0);
                j++;
            }
//            boardGrid.add(gridPane, 1, i);
        }
    }

    public void setAppController(AppController appController) {
        this.appController = appController;
    }

    public void setCommandRegistry(CommandRegistry commandRegistry) {
        this.commandRegistry = commandRegistry;
    }

    private GridPane createRow() {
        GridPane emptySlots = new GridPane();
        emptySlots.setStyle("-fx-alignment: center");
        for (int j = 0; j < 8; j++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(12.5);
            emptySlots.getColumnConstraints().add(column);
        }
        return emptySlots;
    }

    private ImageView createCard(Card card) {
        ImageView cardImage = new ImageView(new Image(getClass().getResourceAsStream("/cards/PNG/" + card.toString() + ".png")));
        cardImage.setFitHeight(120);
//        cardImage.fitHeightProperty().bind(heightProperty());

        cardImage.setPreserveRatio(true);
        cardImage.setSmooth(true);
        cardImage.setPickOnBounds(true);
        DropShadow shadow = new DropShadow();
//Adding the shadow when the mouse cursor is on
        cardImage.addEventHandler(MouseEvent.MOUSE_ENTERED,
                e -> cardImage.setEffect(shadow));
//Removing the shadow when the mouse cursor is off
        cardImage.addEventHandler(MouseEvent.MOUSE_EXITED,
                e -> cardImage.setEffect(null));

        cardImage.setOnMouseClicked((MouseEvent e) -> System.out.println("Card was clicked"));
        return cardImage;
    }

}
