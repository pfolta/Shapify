package uk.ac.standrews.cs.student150018827.cs5001.practical5.controller;

import javafx.stage.Stage;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.about.AboutStage;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.MainWindow;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.newdrawing.NewDrawingStage;

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

    public void closeMainWindow() {
        mainWindow.close();
    }

    public void openNewDrawingDialog(Stage parent) {
        NewDrawingStage newDrawingStage = new NewDrawingStage(this, parent);
        newDrawingStage.show();
        newDrawingStage.requestFocus();
    }

    public void openAboutDialog(Stage parent) {
        AboutStage aboutStage = new AboutStage(this, parent);
        aboutStage.show();
        aboutStage.requestFocus();
    }

    public void exit() {
        mainController.exit();
    }

}