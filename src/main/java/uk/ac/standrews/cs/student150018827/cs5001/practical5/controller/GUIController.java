package uk.ac.standrews.cs.student150018827.cs5001.practical5.controller;

import javafx.stage.Stage;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.about.AboutDialog;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.MainWindow;

public class GUIController {

    private MainController mainController;

    private MainWindow mainWindow;

    public GUIController(MainController mainController, Stage mainStage) {
        this.mainController = mainController;

        this.mainWindow = new MainWindow(this, mainStage);
    }

    public void openMainWindow() {
        mainWindow.open();
    }

    public void exit() {
        mainController.exit();
    }

    public void close() {
        mainWindow.close();
    }

    public void openAboutDialog(Stage parent) {
        AboutDialog aboutDialog = new AboutDialog(this);
        aboutDialog.open(parent);
    }

}