package net.peterfolta.shapify.main;

import javafx.application.Application;
import javafx.stage.Stage;
import net.peterfolta.shapify.controller.MainController;

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