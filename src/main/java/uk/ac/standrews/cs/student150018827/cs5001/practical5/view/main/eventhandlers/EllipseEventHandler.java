package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.eventhandlers;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Ellipse;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.MainController;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.ArtBoard;

public class EllipseEventHandler extends MouseEventHandler {

    private Ellipse ellipse;

    private int originalX;
    private int originalY;

    public EllipseEventHandler(MainController mainController) {
        super(mainController);
    }

    public EventHandler<MouseEvent> getMousePressedEventHandler() {
        return event -> {
            originalX = (int) event.getX();
            originalY = (int) event.getY();

            ellipse = new Ellipse();
            ellipse.setFill(mainController.getDocumentController().getCurrentForeground());
            ellipse.setRadiusX(0);
            ellipse.setRadiusY(0);
            ellipse.setCenterX(originalX);
            ellipse.setCenterY(originalY);
            ellipse.setCursor(Cursor.CROSSHAIR);
            ellipse.setOnMouseMoved(getMouseMovedEventHandler());
            ellipse.setOnMousePressed(getMousePressedEventHandler());
            ellipse.setOnMouseDragged(getMouseDraggedEventHandler());
            ellipse.setOnMouseReleased(getMouseReleasedEventHandler());

            mainController.getDocumentController().getDocument().addObject(ellipse);
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

            int radiusX = Math.abs(x - originalX)/2;
            int radiusY = Math.abs(y - originalY)/2;

            // Adjust width and height if Shift is pressed to create a perfect circle
            if (event.isShiftDown()) {
                radiusX = Math.min(radiusX, radiusY);
                radiusY = Math.min(radiusX, radiusY);
            }

            int centerX = originalX + radiusX;
            int centerY = originalY + radiusY;

            if (x < originalX) {
                centerX = originalX - radiusX;
            }

            if (y < originalY) {
                centerY = originalY - radiusY;
            }

            ellipse.setRadiusX(radiusX);
            ellipse.setRadiusY(radiusY);

            ellipse.setCenterX(centerX);
            ellipse.setCenterY(centerY);

            //mainScene.getStatusBar().setCoordinatesLabel(x, y);
        };
    }

    public EventHandler<MouseEvent> getMouseReleasedEventHandler() {
        return event -> {
            ellipse = null;
        };
    }

}