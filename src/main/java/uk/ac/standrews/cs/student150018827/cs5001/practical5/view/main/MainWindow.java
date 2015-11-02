package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main;

import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.GUIController;

public class MainWindow {

    private GUIController guiController;
    private Stage mainStage;

    private MainScene mainScene;

    public MainWindow(GUIController guiController, Stage mainStage) {
        this.guiController = guiController;
        this.mainStage = mainStage;

        setup();
    }

    private void setup() {
        mainScene = new MainScene(guiController);

        mainStage.setTitle("Drawing Program");
        mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                guiController.exit();
                event.consume();
            }
        });

        mainStage.setScene(mainScene.getScene());
    }

    public void open() {
        mainStage.show();
        mainStage.requestFocus();
    }

    public void close() {
        mainStage.close();
    }

}