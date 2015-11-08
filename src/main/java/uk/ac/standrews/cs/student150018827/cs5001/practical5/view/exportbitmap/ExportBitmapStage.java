package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.exportbitmap;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.MainController;

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