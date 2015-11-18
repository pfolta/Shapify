package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.eventhandlers;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.HistoryController;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.MainController;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.GUIState;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.objects.Rectangle;

public class RectangleEventHandler extends MouseEventHandler {

    private Rectangle rectangle;

    private int originalX;
    private int originalY;

    public RectangleEventHandler(MainController mainController) {
        super(mainController);
    }

    @Override
    public EventHandler<MouseEvent> getMouseExitedEventHandler() {
        return null;
    }

    @Override
    public EventHandler<MouseEvent> getMousePressedEventHandler() {
        return event -> {
            if (event.isPrimaryButtonDown()) {
                GUIState guiState = mainController.getGUIController().getGuiState();

                // Reset selected object
                guiState.setSelectedObject(null);

                originalX = (int) event.getX();
                originalY = (int) event.getY();

                rectangle = new Rectangle();
                rectangle.setFill(guiState.getFillColor());
                rectangle.setStroke(guiState.getStrokeColor());
                rectangle.setStrokeWidth(guiState.getStrokeWidth().getStrokeWidth());

                rectangle.setHeight(1);
                rectangle.setWidth(1);
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

    @Override
    public EventHandler<MouseEvent> getMouseDraggedEventHandler() {
        return event -> {
            super.getMouseDraggedEventHandler().handle(event);

            int x = (int) event.getX();
            int y = (int) event.getY();

            if (event.isPrimaryButtonDown()) {
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

    @Override
    public EventHandler<MouseEvent> getMouseReleasedEventHandler() {
        return event -> {
            // Set selected object to the currently drawn one
            mainController.getGUIController().getGuiState().setSelectedObject(rectangle);

            // Create History Point
            HistoryController.getInstance(mainController).createHistoryPoint();

            rectangle = null;
        };
    }

}