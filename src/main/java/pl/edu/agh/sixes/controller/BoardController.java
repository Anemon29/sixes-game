package pl.edu.agh.sixes.controller;


import javafx.beans.binding.Binding;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import pl.edu.agh.sixes.command.Command;
import pl.edu.agh.sixes.command.CommandRegistry;
import pl.edu.agh.sixes.command.builder.CommandBuilder;
import pl.edu.agh.sixes.controller.util.ImageProvider;
import pl.edu.agh.sixes.model.Board;
import pl.edu.agh.sixes.model.CardContainer;
import pl.edu.agh.sixes.model.Row;

import java.util.List;

public class BoardController {

    private AppController appController;

    private CommandRegistry commandRegistry;

    private CardContainer cardContainer;

    private Board board;

    private CardContainer clicked;

    private boolean afterFirstClick = false;

    private ImageProvider imageProvider;

    @FXML
    private GridPane boardGrid;
    @FXML
    private GridPane rowsGrid;

    @FXML
    private void initialize() {

        rowsGrid.setStyle("-fx-alignment: center");
        for (int i = 0; i < 4; i++) {
            GridPane emptySlots = createRow();
            for (int j = 0; j < 8; j++) {
                emptySlots.add(new Rectangle(80, 122), j, 0);
            }
            rowsGrid.add(emptySlots, 0, i);
        }
    }

    public void setBoard(Board board) {
        this.board = board;
        this.imageProvider = new ImageProvider();
        List<Row> rows = board.getRows();
        for (int i = 0; i < 4; i++) {
            ObservableList<CardContainer> row = rows.get(i).getObservableCardsRow();
            GridPane gridPane = (GridPane) rowsGrid.getChildren().get(i);
            int j = 0;
            for (CardContainer c : row) {
                gridPane.add(createCard(c), j, 0);
                j++;
            }
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

    private void handelClick(CardContainer cardContainer) {
        Command command = new CommandBuilder(board, cardContainer).build();
        this.commandRegistry.executeCommand(command);
    }

    private void handlePairClick(CardContainer first, CardContainer second) {
        Command command = new CommandBuilder(board, first, second).build2();
        this.commandRegistry.executeCommand(command);
    }

    private ImageView createCard(CardContainer cardContainer) {
        String imagePath = cardContainer.getCardImagePath();
        ImageView cardImage = new ImageView(imageProvider.getCardImage(imagePath));
        cardImage.setFitHeight(120);
//        cardImage.fitHeightProperty().bind(heightProperty());

        cardImage.setPreserveRatio(true);
        cardImage.setSmooth(true);
        cardImage.setPickOnBounds(true);
        DropShadow shadow = new DropShadow();
        DropShadow highlightShadow = new DropShadow();
        highlightShadow.setColor(Color.color(1, 0, 0));
        highlightShadow.setRadius(5.0);
//Adding the shadow when the mouse cursor is on
        cardImage.addEventHandler(MouseEvent.MOUSE_ENTERED,
                e -> cardImage.setEffect(shadow));
//Removing the shadow when the mouse cursor is off
        cardImage.addEventHandler(MouseEvent.MOUSE_EXITED,
                e -> cardImage.setEffect(null));

        cardImage.setOnMouseClicked((MouseEvent e) -> {
            if (afterFirstClick){
                afterFirstClick = false;
                handlePairClick(cardContainer, clicked);
            }
            else{
                clicked = cardContainer;
                afterFirstClick = true;
            }

            //handelClick(cardContainer);
            //System.out.println(cardContainer.toString());
        });
        return cardImage;
    }

}
