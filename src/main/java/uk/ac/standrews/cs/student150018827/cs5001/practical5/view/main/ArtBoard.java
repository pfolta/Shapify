package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.MainController;

public class ArtBoard extends Canvas {

    private MainController mainController;

    private MainScene mainScene;

    public ArtBoard(MainController mainController, int width, int height) {
        super(width, height);

        this.mainController = mainController;

        mainScene = mainController.getGUIController().getMainWindow().getMainScene();

        setCursor(Cursor.CROSSHAIR);

        setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int x = (int) event.getX();
                int y = (int) event.getY();

                mainScene.getStatusBar().setCoordinatesLabel(x, y);
            }
        });

        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mainScene.getStatusBar().clearCoordinatesLabel();
            }
        });

        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Mouse Clicked!");
            }
        });

        setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int x = (int) event.getX();
                int y = (int) event.getY();

                // Ensure event is within artboard boundaries
                if (x < 0 || x > getWidth() || y < 0 || y > getHeight()) {
                    mainScene.getStatusBar().clearCoordinatesLabel();
                    event.consume();
                    return;
                }

                mainScene.getStatusBar().setCoordinatesLabel(x, y);

                System.out.println("Mouse Dragged!");
            }
        });

        for (int i = 0; i <= width; i += 10) {
            for (int j = 0; j <= height; j += 10) {
                if (i % 20 == 0 && j % 20 != 0 || i % 20 != 0 && j % 20 == 0) {
                    getGraphicsContext2D().setFill(Color.rgb(225, 225, 225));
                } else {
                    getGraphicsContext2D().setFill(Color.rgb(255, 255, 255));
                }

                getGraphicsContext2D().fillRect(i, j, 10, 10);
            }
        }
    }

}