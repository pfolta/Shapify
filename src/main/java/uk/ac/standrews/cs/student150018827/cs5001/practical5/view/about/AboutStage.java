package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.about;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.GUIController;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.MainController;

public class AboutStage extends Stage {

    private MainController mainController;

    public AboutStage(MainController mainController, Stage parent) {
        super();

        this.mainController = mainController;

        setTitle("About");
        setResizable(false);
        initStyle(StageStyle.UTILITY);
        initModality(Modality.WINDOW_MODAL);
        initOwner(parent);
        setScene(new AboutScene());
    }

}