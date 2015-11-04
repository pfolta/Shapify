package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main;

import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.MainController;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.eventhandlers.MouseEventHandler;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.eventhandlers.RectangleEventHandler;

public class ArtBoard extends Canvas {

    private MainController mainController;

    private MouseEventHandler mouseEventHandler;

    public ArtBoard(MainController mainController, int width, int height) {
        super(width, height);

        this.mainController = mainController;

        mouseEventHandler = new RectangleEventHandler(mainController);

        setCursor(Cursor.CROSSHAIR);

        setOnMouseMoved(mouseEventHandler.getMouseMovedEventHandler());
        setOnMouseExited(mouseEventHandler.getMouseExitedEventHandler());
        setOnMousePressed(mouseEventHandler.getMousePressedEventHandler());
        setOnMouseDragged(mouseEventHandler.getMouseDraggedEventHandler());
        setOnMouseReleased(mouseEventHandler.getMouseReleasedEventHandler());

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