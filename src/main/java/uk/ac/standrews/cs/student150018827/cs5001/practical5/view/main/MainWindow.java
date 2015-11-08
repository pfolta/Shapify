package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.MainController;

import java.io.File;

public class MainWindow {

    private MainController mainController;
    private Stage mainStage;

    private MainScene mainScene;

    public MainWindow(MainController mainController, Stage mainStage) {
        this.mainController = mainController;
        this.mainStage = mainStage;

        setup();
    }

    private void setup() {
        mainScene = new MainScene(mainController, this);

        mainStage.setTitle("Drawing Program");
        mainStage.setFullScreenExitKeyCombination(new KeyCodeCombination(KeyCode.F11));
        mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                mainController.exit();
                event.consume();
            }
        });

        mainStage.setScene(mainScene);
    }

    public void open() {
        mainStage.show();
        mainStage.requestFocus();
    }

    public MainScene getMainScene() {
        return mainScene;
    }

    public void close() {
        mainStage.close();
    }

    public void setFullscreen(boolean fullscreen) {
        mainStage.setFullScreen(fullscreen);
    }

    public void setTitle(String title) {
        if (title != null) {
            mainStage.setTitle(title + " - Drawing Program");
        } else {
            mainStage.setTitle("Drawing Program");
        }
    }

    public Stage getMainStage() {
        return mainStage;
    }

}