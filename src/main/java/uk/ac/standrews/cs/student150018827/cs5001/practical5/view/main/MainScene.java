package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.GUIController;

public class MainScene {

    private GUIController guiController;

    private Scene scene;

    public MainScene(GUIController guiController) {
        this.guiController = guiController;

        BorderPane pane = new BorderPane();

        pane.setTop(new MainMenu(guiController));

        scene = new Scene(pane, 1024, 768);
    }

    public Scene getScene() {
        return scene;
    }

}