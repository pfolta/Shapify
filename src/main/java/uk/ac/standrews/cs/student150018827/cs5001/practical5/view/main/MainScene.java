package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.GUIController;

public class MainScene {

    private GUIController guiController;
    private MainWindow mainWindow;

    private Scene scene;

    private BorderPane borderPane;

    private MainMenuBar mainMenuBar;
    private MainToolBar mainToolBar;

    public MainScene(GUIController guiController, MainWindow mainWindow) {
        this.guiController = guiController;
        this.mainWindow = mainWindow;

        borderPane = new BorderPane();

        VBox vBox = new VBox();

        mainMenuBar = new MainMenuBar(this.guiController, this.mainWindow);
        mainToolBar = new MainToolBar(this.guiController, this.mainWindow);

        vBox.getChildren().addAll(mainMenuBar, mainToolBar);

        borderPane.setTop(vBox);

        setArtBoard(640, 480);

        scene = new Scene(borderPane, 1024, 768);
    }

    public Scene getScene() {
        return scene;
    }

    public void setArtBoard(int width, int height) {
        Group group = new Group();
        StackPane stackPane = new StackPane(group);

        ScrollPane scrollPane = new ScrollPane(stackPane);
        Canvas canvas = new Canvas(width, height);

        for (int i = 0; i <= width; i += 10) {
            for (int j = 0; j <= height; j += 10) {
                if (i % 20 == 0 && j % 20 != 0 || i % 20 != 0 && j % 20 == 0) {
                    canvas.getGraphicsContext2D().setFill(Color.rgb(225, 225, 225));
                } else {
                    canvas.getGraphicsContext2D().setFill(Color.rgb(255, 255, 255));
                }

                canvas.getGraphicsContext2D().fillRect(i, j, 10, 10);
            }
        }

        group.getChildren().addAll(canvas);

        borderPane.setCenter(scrollPane);
    }

}