package net.peterfolta.shapify.view.main.eventhandlers;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import net.peterfolta.shapify.controller.HistoryController;
import net.peterfolta.shapify.controller.MainController;
import net.peterfolta.shapify.model.objects.Ellipse;
import net.peterfolta.shapify.model.GUIState;

public class EllipseEventHandler extends MouseEventHandler {

    private Ellipse ellipse;

    private int originalX;
    private int originalY;

    public EllipseEventHandler(MainController mainController) {
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

                ellipse = new Ellipse();
                ellipse.setFill(guiState.getFillColor());
                ellipse.setStroke(guiState.getStrokeColor());
                ellipse.setStrokeWidth(guiState.getStrokeWidth().getStrokeWidth());

                ellipse.setRadiusX(1);
                ellipse.setRadiusY(1);
                ellipse.setCenterX(originalX);
                ellipse.setCenterY(originalY);
                ellipse.setCursor(Cursor.CROSSHAIR);
                ellipse.setOnMouseMoved(getMouseMovedEventHandler());
                ellipse.setOnMousePressed(getMousePressedEventHandler());
                ellipse.setOnMouseDragged(getMouseDraggedEventHandler());
                ellipse.setOnMouseReleased(getMouseReleasedEventHandler());

                ellipse.setOnContextMenuRequested(new ContextEventHandler(mainController));

                mainController.getDocumentController().addObject(ellipse);
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
                int radiusX = Math.abs(x - originalX) / 2;
                int radiusY = Math.abs(y - originalY) / 2;

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
            }

            mainScene.getStatusBar().setCoordinatesLabel(x, y);
            changed = true;
        };
    }

    @Override
    public EventHandler<MouseEvent> getMouseReleasedEventHandler() {
        return event -> {
            // Set selected object to the currently drawn one
            mainController.getGUIController().getGuiState().setSelectedObject(ellipse);

            // Create History Point
            HistoryController.getInstance(mainController).createHistoryPoint();

            ellipse = null;
        };
    }

}