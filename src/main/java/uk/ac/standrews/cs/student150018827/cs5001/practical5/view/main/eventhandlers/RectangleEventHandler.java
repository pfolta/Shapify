package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.eventhandlers;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.MainController;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.ArtBoard;

public class RectangleEventHandler extends MouseEventHandler {

    private Rectangle rectangle;

    private int originalX;
    private int originalY;

    public RectangleEventHandler(MainController mainController) {
        super(mainController);
    }

    public EventHandler<MouseEvent> getMousePressedEventHandler() {
        return event -> {
            originalX = (int) event.getX();
            originalY = (int) event.getY();

            rectangle = new Rectangle();
            rectangle.setFill(mainController.getDocumentController().getCurrentForeground());
            rectangle.setHeight(0);
            rectangle.setWidth(0);
            rectangle.setX(originalX);
            rectangle.setY(originalY);
            rectangle.setCursor(Cursor.CROSSHAIR);
            rectangle.setOnMouseMoved(getMouseMovedEventHandler());
            rectangle.setOnMousePressed(getMousePressedEventHandler());
            rectangle.setOnMouseDragged(getMouseDraggedEventHandler());
            rectangle.setOnMouseReleased(getMouseReleasedEventHandler());

            mainController.getDocumentController().getDocument().addObject(rectangle);
        };
    }

    public EventHandler<MouseEvent> getMouseDraggedEventHandler() {
        return event -> {
            ArtBoard artBoard = mainScene.getArtBoard();

            int x = (int) event.getX();
            int y = (int) event.getY();

            // Ensure event is within artboard boundaries
            if (x < 0) {
                x = 0;
            }

            if (x > artBoard.getWidth()) {
                x = (int) artBoard.getWidth();
            }

            if (y < 0) {
                y = 0;
            }

            if (y > artBoard.getHeight()) {
                y = (int) artBoard.getHeight();
            }



            if (x < originalX) {
                rectangle.setX(x);
            }

            if (y < originalY) {
                rectangle.setY(y);
            }

            rectangle.setWidth(Math.abs(x - originalX));
            rectangle.setHeight(Math.abs(y - originalY));

            //mainScene.getStatusBar().setCoordinatesLabel(x, y);
        };
    }

    public EventHandler<MouseEvent> getMouseReleasedEventHandler() {
        return event -> {
            rectangle = null;
        };
    }

}