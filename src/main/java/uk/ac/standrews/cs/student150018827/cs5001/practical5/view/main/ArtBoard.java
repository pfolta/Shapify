package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.MainController;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.eventhandlers.MouseEventHandler;

public class ArtBoard extends Canvas {

    private MainController mainController;

    public ArtBoard(MainController mainController, int width, int height) {
        super(width, height);

        this.mainController = mainController;

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

    public void setMouseEventHandler(MouseEventHandler mouseEventHandler) {
        setOnMouseMoved(mouseEventHandler.getMouseMovedEventHandler());
        setOnMouseExited(mouseEventHandler.getMouseExitedEventHandler());
        setOnMousePressed(mouseEventHandler.getMousePressedEventHandler());
        setOnMouseDragged(mouseEventHandler.getMouseDraggedEventHandler());
        setOnMouseReleased(mouseEventHandler.getMouseReleasedEventHandler());
    }
}