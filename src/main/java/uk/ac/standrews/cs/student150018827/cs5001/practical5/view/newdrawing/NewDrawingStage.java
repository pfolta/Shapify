package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.newdrawing;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.MainController;

public class NewDrawingStage extends Stage {

    private MainController mainController;

    public NewDrawingStage(MainController mainController, Stage parent) {
        super();

        this.mainController = mainController;

        setTitle("New Drawing");
        setResizable(false);
        initStyle(StageStyle.UTILITY);
        initModality(Modality.WINDOW_MODAL);
        initOwner(parent);

        NewDrawingScene newDrawingScene = new NewDrawingScene(this.mainController);

        setScene(newDrawingScene);
    }

}