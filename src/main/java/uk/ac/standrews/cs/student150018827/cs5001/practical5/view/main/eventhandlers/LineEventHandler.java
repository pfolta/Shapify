package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.eventhandlers;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.MainController;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.ArtBoard;

public class LineEventHandler extends MouseEventHandler {

    private Line line;

    private int originalX;
    private int originalY;

    public LineEventHandler(MainController mainController) {
        super(mainController);
    }

    public EventHandler<MouseEvent> getMousePressedEventHandler() {
        return event -> {
            originalX = (int) event.getX();
            originalY = (int) event.getY();

            line = new Line();
            line.setFill(mainController.getDocumentController().getCurrentForeground());
            line.setStrokeWidth(3);
            line.setStartX(originalX);
            line.setStartY(originalY);
            line.setEndX(originalX);
            line.setEndY(originalY);
            line.setCursor(Cursor.CROSSHAIR);
            line.setOnMouseMoved(getMouseMovedEventHandler());
            line.setOnMousePressed(getMousePressedEventHandler());
            line.setOnMouseDragged(getMouseDraggedEventHandler());
            line.setOnMouseReleased(getMouseReleasedEventHandler());

            mainController.getDocumentController().getDocument().addObject(line);
        };
    }

    public EventHandler<MouseEvent> getMouseDraggedEventHandler() {
        return event -> {
            ArtBoard artBoard = mainScene.getArtBoard();

            int x = (int) event.getX();
            int y = (int) event.getY();

            // Ensure event is within artboard boundaries
            x = Math.max(x, 0);
            x = Math.min(x, (int) artBoard.getWidth());

            y = Math.max(y, 0);
            y = Math.min(y, (int) artBoard.getHeight());

            int endx = x;
            int endy = y;

            // Adjust width and height if Shift is pressed to create a perfectly straight line
            if (event.isShiftDown()) {
                if (Math.abs(x) > Math.abs(y)) {
                    endy = originalY;
                } else if (Math.abs(y) > Math.abs(x)) {
                    endx = originalX;
                }
            }

            line.setEndX(endx);
            line.setEndY(endy);

            //mainScene.getStatusBar().setCoordinatesLabel(x, y);
        };
    }

    public EventHandler<MouseEvent> getMouseReleasedEventHandler() {
        return event -> {
            line = null;
        };
    }

}