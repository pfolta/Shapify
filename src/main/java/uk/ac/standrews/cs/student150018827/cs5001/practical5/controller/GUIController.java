package uk.ac.standrews.cs.student150018827.cs5001.practical5.controller;

import javafx.stage.Stage;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.MainWindow;

public class GUIController {

    private MainWindow mainWindow;

    public GUIController(Stage mainStage) {
        this.mainWindow = new MainWindow(mainStage);
    }

}