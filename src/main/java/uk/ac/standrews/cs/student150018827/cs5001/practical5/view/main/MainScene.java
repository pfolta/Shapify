package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main;

import javafx.beans.binding.Bindings;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
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

    private MenuBar mainMenuBar;
    private ToolBar mainToolBar;

    private StatusBar statusBar;

    public MainScene(GUIController guiController, MainWindow mainWindow) {
        this.guiController = guiController;
        this.mainWindow = mainWindow;

        borderPane = new BorderPane();

        VBox vBox = new VBox();

        mainMenuBar = new MenuBar(this.guiController, this.mainWindow);
        mainToolBar = new ToolBar(this.guiController, this.mainWindow);

        vBox.getChildren().addAll(mainMenuBar, mainToolBar);

        borderPane.setTop(vBox);

        setArtBoard(640, 480);

        statusBar = new StatusBar(this.guiController, this.mainWindow);
        borderPane.setBottom(statusBar);

        scene = new Scene(borderPane, 800, 600);
    }

    public Scene getScene() {
        return scene;
    }

    public void setArtBoard(int width, int height) {
        Group group = new Group();
        StackPane stackPane = new StackPane(group);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setContent(stackPane);

        Canvas canvas = new Canvas(width, height);
        canvas.setCursor(Cursor.CROSSHAIR);

        canvas.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int x = (int) event.getX();
                int y = (int) event.getY();

                MainScene.this.statusBar.setCoordinatesLabel(x, y);
            }
        });

        canvas.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                MainScene.this.statusBar.clearCoordinatesLabel();
            }
        });

        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Mouse Clicked!");
            }
        });

        canvas.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int x = (int) event.getX();
                int y = (int) event.getY();

                // Ensure event is within artboard boundaries
                if (x < 0 || x > canvas.getWidth() || y < 0 || y > canvas.getHeight()) {
                    MainScene.this.statusBar.clearCoordinatesLabel();
                    return;
                }

                MainScene.this.statusBar.setCoordinatesLabel(x, y);

                System.out.println("Mouse Dragged!");
            }
        });

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

        stackPane.minWidthProperty().bind(Bindings.createDoubleBinding(() -> scrollPane.getViewportBounds().getWidth(), scrollPane.viewportBoundsProperty()));
        stackPane.minHeightProperty().bind(Bindings.createDoubleBinding(() -> scrollPane.getViewportBounds().getHeight(), scrollPane.viewportBoundsProperty()));

        group.getChildren().addAll(canvas);

        borderPane.setCenter(scrollPane);
    }

    public void clearArtBoard() {
        borderPane.setCenter(null);
    }

}