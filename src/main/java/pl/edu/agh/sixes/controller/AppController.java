package pl.edu.agh.sixes.controller;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pl.edu.agh.sixes.command.CommandRegistry;

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
            BorderPane rootLayout = loader.load();
            rootLayout.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));

            // set initial data into controller
            BoardController controller = loader.getController();
            controller.setAppController(this);
            controller.setCommandRegistry(commandRegistry);

            // add layout to a scene and show them all
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            throw new RuntimeException("Critical error while initializing game.", e);
        }

    }

}
