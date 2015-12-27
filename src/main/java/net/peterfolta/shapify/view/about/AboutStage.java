package net.peterfolta.shapify.view.about;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.peterfolta.shapify.controller.MainController;

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