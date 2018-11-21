package pl.edu.agh.sixes;

import javafx.stage.Stage;
import pl.edu.agh.sixes.controller.AppController;

public class Application extends javafx.application.Application {
    private Stage primaryStage;

    private AppController appController;

    @Override
    public void start(Stage primaryStage) {

        this.primaryStage = primaryStage;

        this.appController = new AppController(primaryStage);
        this.appController.initRootLayout();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
