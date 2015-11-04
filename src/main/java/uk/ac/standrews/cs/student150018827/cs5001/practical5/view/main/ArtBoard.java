package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main;

import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.MainController;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.DrawTools;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.eventhandlers.EllipseEventHandler;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.eventhandlers.MouseEventHandler;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.eventhandlers.RectangleEventHandler;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.eventhandlers.SelectEventHandler;

public class ArtBoard extends Canvas {

    private MainController mainController;

    private MouseEventHandler mouseEventHandler;

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

    public void setSelectedTool(DrawTools selectedTool) {
        switch (selectedTool) {
            case SELECT_TOOL: {
                setCursor(Cursor.DEFAULT);
                mouseEventHandler = new SelectEventHandler(mainController);
                registerEventHandlers();

                break;
            }
            case RECTANGLE_TOOL: {
                setCursor(Cursor.CROSSHAIR);
                mouseEventHandler = new RectangleEventHandler(mainController);
                registerEventHandlers();

                break;
            }
            case ELLIPSE_TOOL: {
                setCursor(Cursor.CROSSHAIR);
                mouseEventHandler = new EllipseEventHandler(mainController);
                registerEventHandlers();

                break;
            }
        }
    }

    private void registerEventHandlers() {
        setOnMouseMoved(mouseEventHandler.getMouseMovedEventHandler());
        setOnMouseExited(mouseEventHandler.getMouseExitedEventHandler());
        setOnMousePressed(mouseEventHandler.getMousePressedEventHandler());
        setOnMouseDragged(mouseEventHandler.getMouseDraggedEventHandler());
        setOnMouseReleased(mouseEventHandler.getMouseReleasedEventHandler());
    }

}