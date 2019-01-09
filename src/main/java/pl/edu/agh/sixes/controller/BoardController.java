package pl.edu.agh.sixes.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import pl.edu.agh.sixes.command.Command;
import pl.edu.agh.sixes.command.CommandRegistry;
import pl.edu.agh.sixes.command.builder.CommandBuilder;
import pl.edu.agh.sixes.controller.util.ImageProvider;
import pl.edu.agh.sixes.model.Board;
import pl.edu.agh.sixes.model.Card;
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
    private GridPane decksGrid;

    @FXML
    private void initialize() {
        for (int i = 0; i < 4; i++) {
            GridPane emptySlots = createRow();
            for (int j = 0; j < 8; j++) {
                emptySlots.add(new Rectangle(80, 122), j, 0);
            }
            rowsGrid.add(emptySlots, 0, i);
        }
        for (int i = 0; i < 3; i++) {
            decksGrid.add(new StackPane(), 0, i);
        }
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

    public void setAppController(AppController appController) {
        this.appController = appController;
    }

    public void setCommandRegistry(CommandRegistry commandRegistry) {
        this.commandRegistry = commandRegistry;
    }

    public void setBoard(Board board) {
        this.board = board;
        this.imageProvider = new ImageProvider();
        List<Row> rows = board.getRows();
        for (int i = 0; i < 4; i++) {
            List<CardContainer> row = rows.get(i).getCardsRow();
            GridPane gridPane = (GridPane) rowsGrid.getChildren().get(i);
            int j = 0;
            for (CardContainer c : row) {
                gridPane.add(createCard(c), j, 0);
                j++;
            }
        }

// deck initalization
        StackPane stackpane = (StackPane) decksGrid.getChildren().get(0);
        ObservableList deckList = stackpane.getChildren();
        ObservableList<Card> deck = board.getDeck().getObservableCards();
        for (int i = 0; i < deck.size() - 1; i++) {
            ImageView back = prepareCard(imageProvider.getCardBack());
            StackPane.setMargin(back, new Insets(0, 0, i, i));
            deckList.add(back);
        }

        initilizaStack(board.getDeck(), deckList);

        // intilize rejected
        StackPane stackpane1 = (StackPane) decksGrid.getChildren().get(1);
        initilizaStack(board.getRejectedCards(), stackpane1.getChildren());


        // initilize trash
        StackPane stackpane2 = (StackPane) decksGrid.getChildren().get(2);
        initilizeStackCount(board.getTrash(),stackpane2.getChildren());

    }

    private void handlePairClick(CardContainer first, CardContainer second) {
        try {
            Command command = new CommandBuilder(board, first, second).build();
            this.commandRegistry.executeCommand(command);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ImageView prepareCard(Image image) {
        ImageView cardImage = new ImageView(image);
        cardImage.setFitHeight(120);
//        cardImage.fitHeightProperty().bind(heightProperty());

        cardImage.setPreserveRatio(true);
        cardImage.setSmooth(true);
        cardImage.setPickOnBounds(true);
        return cardImage;
    }

    private void setMarginOnLastCard(ImageView lastCard, int listSize) {
        StackPane.setMargin(lastCard, new Insets(0, 0, listSize - 1, listSize - 1));
    }

    private ImageView createCard(CardContainer cardContainer) {
        ImageView cardImage = prepareCard(imageProvider.getCardImage(cardContainer));
        cardContainer.getContentProperty().addListener(new ChangeListener<Card>() {
            @Override
            public void changed(ObservableValue<? extends Card> observable, Card oldValue, Card newValue) {
                cardImage.setImage(imageProvider.getCardImage(cardContainer));
            }
        });

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
                if (!cardContainer.equals(clicked)) {
                    handlePairClick(clicked, cardContainer);
                }
            }
            else{
                clicked = cardContainer;
                afterFirstClick = true;
            }

            //System.out.println(cardContainer.toString());
        });
        return cardImage;
    }

    private void initilizaStack(CardsStack cardsStack, ObservableList list) {
        ImageView topCard = createCard(cardsStack.getContainer());
        setMarginOnLastCard(topCard, list.size());
        list.add(topCard);
        initilizeStackCount(cardsStack, list);
    }

    private void initilizeStackCount(CardsStack cardsStack, ObservableList list) {
        cardsStack.getObservableCards().addListener((ListChangeListener<Card>) c -> {
            while (c.next()) {
                ImageView cover = (ImageView) list.get(list.size() - 1);
                if (c.wasAdded()) {
                    ImageView back = prepareCard(imageProvider.getCardBack());
                    setMarginOnLastCard(back, list.size());
                    list.remove(list.size() - 1);
                    list.add(back);
                    setMarginOnLastCard(cover, list.size());
                    list.add(cover);
                }
                if (c.wasRemoved()) {
                    list.remove(list.size() - 2);
                    setMarginOnLastCard(cover, list.size());
                }
            }
        });
    }
}
