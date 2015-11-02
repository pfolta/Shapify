package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.GUIController;

public class MainScene {

    private GUIController guiController;

    private Scene scene;

    private MainMenuBar mainMenuBar;
    private MainToolBar mainToolBar;

    public MainScene(GUIController guiController) {
        this.guiController = guiController;

        BorderPane pane = new BorderPane();

        VBox vBox = new VBox();

        mainMenuBar = new MainMenuBar(guiController);
        mainToolBar = new MainToolBar(guiController);

        vBox.getChildren().addAll(mainMenuBar, mainToolBar);

        pane.setTop(vBox);

        scene = new Scene(pane, 1024, 768);
    }

    public Scene getScene() {
        return scene;
    }

}