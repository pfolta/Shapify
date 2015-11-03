package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.newdrawing;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.GUIController;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.MainScene;

public class NewDrawingStage extends Stage {

    private GUIController guiController;

    public NewDrawingStage(GUIController guiController, Stage parent) {
        super();

        this.guiController = guiController;

        setTitle("New Drawing");
        setResizable(false);
        initStyle(StageStyle.UTILITY);
        initModality(Modality.WINDOW_MODAL);
        initOwner(parent);

        NewDrawingScene newDrawingScene = new NewDrawingScene();

        setScene(newDrawingScene.getScene());
    }

}