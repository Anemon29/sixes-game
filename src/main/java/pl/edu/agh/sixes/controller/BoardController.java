package pl.edu.agh.sixes.controller;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
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
import pl.edu.agh.sixes.model.CardsStack;
import pl.edu.agh.sixes.model.Row;

import java.util.List;

public class BoardController {

    private AppController appController;

    private CommandRegistry commandRegistry;

    private Board board;

    private ObjectProperty<CardContainer> clicked;

    private ImageProvider imageProvider;

    @FXML
    private GridPane rowsGrid;
    @FXML
    private GridPane decksGrid;

    public void setAppController(AppController appController) {
        this.appController = appController;
    }

    public void setCommandRegistry(CommandRegistry commandRegistry) {
        this.commandRegistry = commandRegistry;
    }

    public void setBoard(Board board) {
        this.board = board;
        this.imageProvider = new ImageProvider();

        initializeRows();
        initializeDeck();
        initializeRejected();
        initializeTrash();
        addButtons();
    }

    private CardContainer getClicked() {
        return clicked.get();
    }

    private ObjectProperty<CardContainer> clickedProperty() {
        return clicked;
    }

    private void setClicked(CardContainer clicked) {
        this.clicked.set(clicked);
    }

    @FXML
    private void initialize() {
        this.clicked = new SimpleObjectProperty<>(null);
        for (int i = 0; i < 4; i++) {
            GridPane emptySlots = createRow(8);
            for (int j = 0; j < 8; j++) {
                StackPane stackPane = new StackPane();
                stackPane.getChildren().add(new Rectangle(80, 122));
                emptySlots.add(stackPane, j, 0);
            }
            rowsGrid.add(emptySlots, 0, i);
        }
        for (int i = 0; i < 3; i++) {
            decksGrid.add(new StackPane(), 0, i);
        }
    }

    private void initializeStack(CardsStack cardsStack, ObservableList<Node> list) {
        ImageView topCard = createCard(cardsStack.getContainer());
        setMarginOnLastCard(topCard, list.size());
        list.add(topCard);
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

    private void initializeRows() {
        List<Row> rows = board.getRows();
        for (int i = 0; i < 4; i++) {
            Row row = rows.get(i);
            List<CardContainer> cardsRow = row.getCardsRow();
            GridPane gridPane = (GridPane) rowsGrid.getChildren().get(i);
            int j = 0;
            for (CardContainer c : cardsRow) {
                StackPane cardPlace = (StackPane) gridPane.getChildren().get(j);
                ImageView colorImage = prepareSuit();
                row.suitProperty()
                        .addListener(
                                (ObservableValue<? extends Card.Suit> observable,
                                 Card.Suit oldValue,
                                 Card.Suit newValue) -> colorImage.setImage(imageProvider.getSuitImage(newValue))
                        );
                cardPlace.getChildren().addAll(colorImage, createCard(c));
                j++;
            }
        }
    }

    private void initializeDeck() {
        ObservableList<Card> deck = board.getDeck().getObservableCards();
        StackPane stackpane = (StackPane) decksGrid.getChildren().get(0);
        ObservableList<Node> deckList = stackpane.getChildren();
        for (int i = 0; i < deck.size() - 1; i++) {
            ImageView back = prepareCard(imageProvider.getCardBack());
            StackPane.setMargin(back, new Insets(0, 0, i, i));
            deckList.add(back);
        }

        initializeStack(board.getDeck(), deckList);
    }

    private void initializeRejected() {
        StackPane stackpane1 = (StackPane) decksGrid.getChildren().get(1);
        initializeStack(board.getRejectedCards(), stackpane1.getChildren());
    }

    private void initializeTrash() {
        StackPane stackpane = (StackPane) decksGrid.getChildren().get(2);
        ObservableList<Node> trashList = stackpane.getChildren();
        board.getTrash().getObservableCards()
                .addListener((ListChangeListener<Card>) c ->
                {
                    while (c.next()) {
                        if (c.wasAdded()) {
                            ImageView back = prepareCard(imageProvider.getCardBack());
                            setMarginOnLastCard(back, trashList.size());
                            trashList.add(back);
                        }
                        if (c.wasRemoved()) {
                            trashList.remove(trashList.size() - 1);
                        }
                    }
                });
    }

    private void addButtons() {
        GridPane buttonsPane = createRow(2);
        Button forwardButton = new Button("Forward");
        forwardButton.setOnAction((
                ActionEvent e) -> commandRegistry.redo());
        DropShadow shadow = new DropShadow();
        forwardButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
                e -> forwardButton.setEffect(shadow));
        //Removing the shadow when the mouse cursor is off
        forwardButton.addEventHandler(MouseEvent.MOUSE_EXITED,
                e -> forwardButton.setEffect(null));

        Button reverseButton = new Button("Revers");
        reverseButton.setOnAction((
                ActionEvent e) -> commandRegistry.undo());
        reverseButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
                e -> reverseButton.setEffect(shadow));
        //Removing the shadow when the mouse cursor is off
        reverseButton.addEventHandler(MouseEvent.MOUSE_EXITED,
                e -> reverseButton.setEffect(null));
        buttonsPane.add(reverseButton, 0, 0);
        buttonsPane.add(forwardButton, 1, 0);
        decksGrid.add(buttonsPane, 0, 3);
    }

    private GridPane createRow(int size) {
        GridPane emptySlots = new GridPane();
        emptySlots.setId("row");
        int width = 100 / size;
        for (int j = 0; j < size; j++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(width);
            emptySlots.getColumnConstraints().add(column);
        }
        return emptySlots;
    }

    private ImageView prepareCard(Image image) {
        ImageView cardImage = new ImageView(image);
        cardImage.setFitHeight(120);
//        cardImage.fitHeightProperty().bind(heightProperty());
        return prepareImage(cardImage);
    }

    private ImageView prepareSuit() {
        ImageView colorImage = prepareImage(new ImageView());
        colorImage.setFitWidth(68);
        colorImage.setId("suit");
        return colorImage;
    }

    private ImageView prepareImage(ImageView imageView) {
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setPickOnBounds(true);
        return imageView;
    }

    private void handleClick(CardContainer cardContainer) {
        if (getClicked() != null) {
            if (!cardContainer.equals(getClicked())) {
                try {
                    Command command = new CommandBuilder(board, getClicked(), cardContainer).build();
                    this.commandRegistry.executeCommand(command);
                    setClicked(null);
                } catch (Exception err) {
                    setClicked(cardContainer);
                    err.printStackTrace();
                }
            }
        } else {
            setClicked(cardContainer);
        }
    }

    private ImageView createCard(CardContainer cardContainer) {
        Card card = null;
        if (cardContainer.getContent().isPresent()) {
            card = cardContainer.getContent().get();
        }
        ImageView cardImage = prepareCard(imageProvider.getCardImage(card));
        cardContainer.getContentProperty()
                .addListener((ObservableValue<? extends Card> observable, Card oldValue, Card newValue) ->
                        cardImage.setImage(imageProvider.getCardImage(newValue))
                );
        DropShadow highlightShadow = new DropShadow();
        highlightShadow.setColor(Color.web("0x00f9ff"));
        highlightShadow.setRadius(20.0);

        clickedProperty()
                .addListener((ObservableValue<? extends CardContainer> observable,
                              CardContainer oldValue,
                              CardContainer newValue) -> {
                    if (cardContainer.equals(newValue)) {
                        cardImage.setEffect(highlightShadow);
                    } else if (cardContainer.equals(oldValue)) {
                        cardImage.setEffect(null);
                    }
                });

        DropShadow shadow = new DropShadow();
        //Adding the shadow when the mouse cursor is on
        cardImage.addEventHandler(MouseEvent.MOUSE_ENTERED,
                e -> {
                    if (!cardContainer.equals(getClicked())) cardImage.setEffect(shadow);
                });
        //Removing the shadow when the mouse cursor is off
        cardImage.addEventHandler(MouseEvent.MOUSE_EXITED,
                e -> {
                    if (!cardContainer.equals(getClicked())) cardImage.setEffect(null);
                });
        cardImage.setOnMouseClicked((MouseEvent e) -> handleClick(cardContainer));
        return cardImage;
    }

    private ImageView createSuit(CardContainer cardContainer) {
        Card card = null;
        if (cardContainer.getContent().isPresent()) {
            card = cardContainer.getContent().get();
        }
        ImageView cardImage = prepareCard(imageProvider.getCardImage(card));
        cardContainer.getContentProperty()
                .addListener((ObservableValue<? extends Card> observable, Card oldValue, Card newValue) ->
                        cardImage.setImage(imageProvider.getCardImage(newValue))
                );
        DropShadow highlightShadow = new DropShadow();
        highlightShadow.setColor(Color.web("0x00f9ff"));
        highlightShadow.setRadius(20.0);

        clickedProperty()
                .addListener((ObservableValue<? extends CardContainer> observable,
                              CardContainer oldValue,
                              CardContainer newValue) -> {
                    if (cardContainer.equals(newValue)) {
                        cardImage.setEffect(highlightShadow);
                    } else if (cardContainer.equals(oldValue)) {
                        cardImage.setEffect(null);
                    }
                });

        DropShadow shadow = new DropShadow();
        //Adding the shadow when the mouse cursor is on
        cardImage.addEventHandler(MouseEvent.MOUSE_ENTERED,
                e -> {
                    if (!cardContainer.equals(getClicked())) cardImage.setEffect(shadow);
                });
        //Removing the shadow when the mouse cursor is off
        cardImage.addEventHandler(MouseEvent.MOUSE_EXITED,
                e -> {
                    if (!cardContainer.equals(getClicked())) cardImage.setEffect(null);
                });
        cardImage.setOnMouseClicked((MouseEvent e) -> handleClick(cardContainer));
        return cardImage;
    }


    private void setMarginOnLastCard(ImageView lastCard, int listSize) {
        StackPane.setMargin(lastCard, new Insets(0, 0, listSize - 1, listSize - 1));
    }

}
