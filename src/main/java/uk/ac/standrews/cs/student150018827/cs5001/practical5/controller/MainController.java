package uk.ac.standrews.cs.student150018827.cs5001.practical5.controller;

import javafx.stage.Stage;

public class MainController {

    private GUIController guiController;

    public MainController() {
    }

    public void initGUI(Stage stage) {
        guiController = new GUIController(stage);
    }

}