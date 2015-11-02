package uk.ac.standrews.cs.student150018827.cs5001.practical5.view;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MainWindow {

    private Stage mainStage;
    private MainScene mainScene;

    public MainWindow(Stage mainStage) {
        this.mainStage = mainStage;

        setup();
    }

    private void setup() {
        mainScene = new MainScene();

        mainStage.setTitle("Drawing Program");

        mainStage.setScene(new Scene(new StackPane(), 300, 300));

        mainStage.show();
    }

}