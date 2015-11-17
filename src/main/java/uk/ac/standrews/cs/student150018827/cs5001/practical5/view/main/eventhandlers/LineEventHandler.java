package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.eventhandlers;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.MainController;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.objects.Line;

public class LineEventHandler extends MouseEventHandler {

    private Line line;

    private int originalX;
    private int originalY;

    public LineEventHandler(MainController mainController) {
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
                // Reset selected object
                mainController.getGUIController().getGuiState().setSelectedObject(null);

                originalX = (int) event.getX();
                originalY = (int) event.getY();

                line = new Line();
                line.setStroke(mainController.getGUIController().getGuiState().getCurrentForeground());
                line.setStrokeWidth(3);
                line.setStartX(originalX);
                line.setStartY(originalY);
                line.setEndX(originalX + 1);
                line.setEndY(originalY + 1);
                line.setCursor(Cursor.CROSSHAIR);
                line.setOnMouseMoved(getMouseMovedEventHandler());
                line.setOnMousePressed(getMousePressedEventHandler());
                line.setOnMouseDragged(getMouseDraggedEventHandler());
                line.setOnMouseReleased(getMouseReleasedEventHandler());

                line.setOnContextMenuRequested(new ContextEventHandler(mainController));

                mainController.getDocumentController().addObject(line);
            }
        };
    }

    @Override
    public EventHandler<MouseEvent> getMouseDraggedEventHandler() {
        return event -> {
            int x = (int) event.getX();
            int y = (int) event.getY();

            if (event.isPrimaryButtonDown()) {
                int endx = x;
                int endy = y;

                // Adjust width and height if Shift is pressed to create a perfectly straight line
                if (event.isShiftDown()) {
                    if (Math.abs(x - originalX) > Math.abs(y - originalY)) {
                        endy = originalY;
                    }

                    if (Math.abs(y - originalY) > Math.abs(x - originalX)) {
                        endx = originalX;
                    }
                }

                line.setEndX(endx);
                line.setEndY(endy);
            }

            mainScene.getStatusBar().setCoordinatesLabel(x, y);
        };
    }

    @Override
    public EventHandler<MouseEvent> getMouseReleasedEventHandler() {
        return event -> {
            // Set selected object to the currently drawn one
            mainController.getGUIController().getGuiState().setSelectedObject(line);

            line = null;
        };
    }

}