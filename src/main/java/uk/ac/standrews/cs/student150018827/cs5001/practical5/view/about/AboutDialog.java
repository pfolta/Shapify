package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.about;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.GUIController;

public class AboutDialog {

    private GUIController guiController;

    private Stage aboutStage;

    public AboutDialog(GUIController guiController) {
        this.guiController = guiController;

        aboutStage = new Stage();
        aboutStage.setTitle("About");
        aboutStage.initStyle(StageStyle.UTILITY);
        aboutStage.initModality(Modality.WINDOW_MODAL);
        aboutStage.setResizable(false);
    }

    public void open(Stage parent) {
        aboutStage.initOwner(parent);

        aboutStage.show();
        aboutStage.requestFocus();
    }

    public void close() {
        aboutStage.close();
    }

}