package pl.edu.agh.sixes.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import pl.edu.agh.sixes.command.CommandRegistry;
import pl.edu.agh.sixes.engine.generator.DeckGenerator;
import pl.edu.agh.sixes.engine.generator.InitialStageGenerator;
import pl.edu.agh.sixes.engine.generator.RowsGenerator;

import java.io.IOException;

public class AppController {

    private Stage primaryStage;

    private CommandRegistry commandRegistry = new CommandRegistry();

    public AppController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void initRootLayout() {
        try {
            this.primaryStage.setTitle("Sixes game");

            //we don't want to change size of out application
            this.primaryStage.setResizable(false);

            // load layout from FXML file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/BoardPane.fxml"));

            GridPane rootLayout = loader.load();
//            rootLayout.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            InitialStageGenerator initialStageGenerator = new InitialStageGenerator(new DeckGenerator(), new RowsGenerator());
            // set initial data into controller
            BoardController controller = loader.getController();
            controller.setAppController(this);
            controller.setCommandRegistry(commandRegistry);
            controller.setBoard(initialStageGenerator.initializeBoard());
            // add layout to a scene and show them all
            Scene scene = new Scene(rootLayout);
            scene.getStylesheets().addAll(getClass().getResource("/style.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            throw new RuntimeException("Critical error while initializing game.", e);
        }

    }

}
