package net.peterfolta.shapify.controller;

import javafx.stage.Stage;
import net.peterfolta.shapify.main.Main;

public class MainController {

    private SvgController svgController;
    private GUIController guiController;
    private DocumentController documentController;

    public MainController(Stage stage) {
        svgController = new SvgController();
        guiController = new GUIController(this, stage);
        documentController = new DocumentController(this);
        guiController.openMainWindow();
    }

    public void exit() {
        if (documentController.closeDocument()) {
            guiController.closeMainWindow();
            Main.exit();
        }
    }

    public SvgController getSvgController() {
        return svgController;
    }

    public GUIController getGUIController() {
        return guiController;
    }

    public DocumentController getDocumentController() {
        return documentController;
    }

}