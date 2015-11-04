package uk.ac.standrews.cs.student150018827.cs5001.practical5.controller;

import javafx.stage.Stage;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.main.Main;

public class MainController {

    private GUIController guiController;
    private DocumentController documentController;

    public MainController(Stage stage) {
        guiController = new GUIController(this, stage);
        documentController = new DocumentController(this);

        guiController.openMainWindow();
    }

    public void exit() {
        guiController.closeMainWindow();
        Main.exit();
    }

    public GUIController getGUIController() {
        return guiController;
    }

    public DocumentController getDocumentController() {
        return documentController;
    }

}