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
            if (event.isPrimaryButtonDown()) {
                // Reset selected object
                mainController.getGUIController().getGuiState().setSelectedObject(null);

                originalX = (int) event.getX();
                originalY = (int) event.getY();

                rectangle = new Rectangle();
                rectangle.setFill(mainController.getGUIController().getGuiState().getCurrentForeground());
                rectangle.setHeight(0);
                rectangle.setWidth(0);
                rectangle.setX(originalX);
                rectangle.setY(originalY);
                rectangle.setCursor(Cursor.CROSSHAIR);
                rectangle.setOnMouseMoved(getMouseMovedEventHandler());
                rectangle.setOnMousePressed(getMousePressedEventHandler());
                rectangle.setOnMouseDragged(getMouseDraggedEventHandler());
                rectangle.setOnMouseReleased(getMouseReleasedEventHandler());

                rectangle.setOnContextMenuRequested(new ContextEventHandler(mainController));

                mainController.getDocumentController().addObject(rectangle);
            }
        };
    }

    public EventHandler<MouseEvent> getMouseDraggedEventHandler() {
        return event -> {
            int x = (int) event.getX();
            int y = (int) event.getY();

            if (event.isPrimaryButtonDown()) {
                ArtBoard artBoard = mainScene.getArtBoard();

                // Ensure event is within artboard boundaries
                x = Math.max(x, 0);
                x = Math.min(x, (int) artBoard.getWidth());

                y = Math.max(y, 0);
                y = Math.min(y, (int) artBoard.getHeight());

                int posx = originalX;
                int posy = originalY;

                int width = Math.abs(x - originalX);
                int height = Math.abs(y - originalY);

                // Adjust width and height if Shift is pressed to create a perfect square
                if (event.isShiftDown()) {
                    width = Math.min(width, height);
                    height = Math.min(width, height);
                }

                if (x < originalX) {
                    posx = originalX - width;
                }

                if (y < originalY) {
                    posy = originalY - height;
                }

                rectangle.setWidth(width);
                rectangle.setHeight(height);

                rectangle.setX(posx);
                rectangle.setY(posy);
            }

            mainScene.getStatusBar().setCoordinatesLabel(x, y);
        };
    }

    public EventHandler<MouseEvent> getMouseReleasedEventHandler() {
        return event -> {
            // Set selected object to the currently drawn one
            mainController.getGUIController().getGuiState().setSelectedObject(rectangle);

            rectangle = null;
        };
    }

}