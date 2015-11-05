package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.eventhandlers;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;
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
            if (event.isPrimaryButtonDown()) {
                originalX = (int) event.getX();
                originalY = (int) event.getY();

                line = new Line();
                line.setStroke(mainController.getGUIController().getGuiState().getCurrentForeground());
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

                line.setOnContextMenuRequested(new ContextEventHandler(mainController));

                mainController.getDocumentController().addObject(line);
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
                x = Math.max(x, (int) (0 + Math.ceil(line.getStrokeWidth()/2.0)));
                x = Math.min(x, (int) (artBoard.getWidth() - Math.ceil(line.getStrokeWidth()/2.0)));

                y = Math.max(y, (int) (0 + Math.ceil(line.getStrokeWidth()/2.0)));
                y = Math.min(y, (int) (artBoard.getHeight() - Math.ceil(line.getStrokeWidth()/2.0)));

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

    public EventHandler<MouseEvent> getMouseReleasedEventHandler() {
        return event -> line = null;
    }

}