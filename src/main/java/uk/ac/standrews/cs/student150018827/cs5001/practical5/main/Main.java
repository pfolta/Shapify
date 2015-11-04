package uk.ac.standrews.cs.student150018827.cs5001.practical5.main;

import javafx.application.Application;
import javafx.stage.Stage;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.MainController;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        new MainController(primaryStage);
    }

    public static void exit() {
        System.exit(0);
    }
}