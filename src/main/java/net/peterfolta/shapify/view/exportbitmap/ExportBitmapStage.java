package net.peterfolta.shapify.view.exportbitmap;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.peterfolta.shapify.controller.MainController;

public class ExportBitmapStage extends Stage {

    private MainController mainController;

    public ExportBitmapStage(MainController mainController, Stage parent) {
        super();

        this.mainController = mainController;

        setTitle("Export Bitmap");
        setResizable(false);
        initStyle(StageStyle.UTILITY);
        initModality(Modality.WINDOW_MODAL);
        initOwner(parent);

        ExportBitmapScene exportBitmapScene = new ExportBitmapScene(mainController);

        setScene(exportBitmapScene);
    }

}