package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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

        Group group = new Group();
        StackPane stackPane = new StackPane(group);

        ScrollPane scrollPane = new ScrollPane(stackPane);
        Canvas canvas = new Canvas(500, 500);
        Rectangle rectangle = new Rectangle(210, 10, 100, 100);
        group.getChildren().add(rectangle);
        canvas.getGraphicsContext2D().fillRect(10, 10, 100, 100);
        canvas.getGraphicsContext2D().setFill(Color.rgb(192, 128, 96));
        canvas.getGraphicsContext2D().fillRect(110, 10, 100, 100);
        group.getChildren().addAll(canvas);


        pane.setTop(vBox);
        pane.setCenter(scrollPane);

        scene = new Scene(pane, 1024, 768);
    }

    public Scene getScene() {
        return scene;
    }

}