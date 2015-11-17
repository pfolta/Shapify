package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.MainController;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.main.Data;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.eventhandlers.MouseEventHandler;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.eventhandlers.SelectEventHandler;

public class ArtBoard extends Canvas {

    private MainController mainController;

    public ArtBoard(MainController mainController, int width, int height) {
        super(width, height);

        this.mainController = mainController;

        paintArtBoardBackground(Data.ARTBOARD_BACKGROUND_TITLE_SIZE);
        setMouseEventHandler(new SelectEventHandler(this.mainController));
    }

    public void paintArtBoardBackground(int tileSize) {
        for (int i = 0; i <= getWidth(); i += tileSize) {
            for (int j = 0; j <= getHeight(); j += tileSize) {
                if (i % (2 * tileSize) == 0 && j % (2 * tileSize) != 0 || i % (2 * tileSize) != 0 && j % (2 * tileSize) == 0) {
                    getGraphicsContext2D().setFill(Color.rgb(225, 225, 225));
                } else {
                    getGraphicsContext2D().setFill(Color.rgb(255, 255, 255));
                }

                getGraphicsContext2D().fillRect(i, j, tileSize, tileSize);
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