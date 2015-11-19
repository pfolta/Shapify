package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.eventhandlers;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.MainController;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.GUIState;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.objects.Ellipse;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.objects.Line;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.objects.Rectangle;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.StrokeWidth;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.ArtBoard;

public class SelectEventHandler extends MouseEventHandler {

    private MoveEventHandler moveEventHandler;
    private ShearEventHandler shearEventHandler;

    public SelectEventHandler(MainController mainController) {
        super(mainController);

        moveEventHandler = new MoveEventHandler(mainController);
        shearEventHandler = new ShearEventHandler(mainController);
    }

    @Override
    public EventHandler<MouseEvent> getMouseExitedEventHandler() {
        return null;
    }

    @Override
    public EventHandler<MouseEvent> getMousePressedEventHandler() {
        return event -> {
            changed = false;

            if (event.isPrimaryButtonDown()) {
                GUIState guiState = mainController.getGUIController().getGuiState();

                // Reset selected object
                guiState.setSelectedObject(null);

                if (!(event.getSource() instanceof ArtBoard)) {
                    Node selectedObject = (Node) event.getSource();

                    if (selectedObject instanceof Rectangle) {
                        Rectangle rectangle = (Rectangle) selectedObject;

                        // Set selected object color
                        guiState.setFillColor(((Color) rectangle.getFill()));
                        guiState.setStrokeColor((Color) rectangle.getStroke());
                        guiState.setStrokeWidth(StrokeWidth.fromInteger((int) rectangle.getStrokeWidth()));
                    }

                    if (selectedObject instanceof Ellipse) {
                        Ellipse ellipse = (Ellipse) selectedObject;

                        // Set selected object color
                        guiState.setFillColor(((Color) ellipse.getFill()));
                        guiState.setStrokeColor((Color) ellipse.getStroke());
                        guiState.setStrokeWidth(StrokeWidth.fromInteger((int) ellipse.getStrokeWidth()));
                    }

                    if (selectedObject instanceof Line) {
                        Line line = (Line) selectedObject;

                        // Set selected object color
                        guiState.setStrokeColor((Color) line.getStroke());
                        guiState.setStrokeWidth(StrokeWidth.fromInteger((int) line.getStrokeWidth()));
                    }

                    // Set selected object
                    guiState.setSelectedObject(selectedObject);

                    // Delegate event
                    if (event.isShiftDown()) {
                        shearEventHandler.getMousePressedEventHandler().handle(event);
                    } else {
                        moveEventHandler.getMousePressedEventHandler().handle(event);
                    }
                }
            }
        };
    }

    public EventHandler<MouseEvent> getMousePressedOutlineEventHandler() {
        return event -> {
            if (event.isPrimaryButtonDown()) {
                if (event.isShiftDown()) {
                    shearEventHandler.getMousePressedOutlineEventHandler().handle(event);
                } else {
                    moveEventHandler.getMousePressedOutlineEventHandler().handle(event);
                }
            }
        };
    }

    @Override
    public EventHandler<MouseEvent> getMouseDraggedEventHandler() {
        return event -> {
            super.getMouseDraggedEventHandler().handle(event);

            if (event.isPrimaryButtonDown()) {
                if (event.isShiftDown()) {
                    shearEventHandler.getMouseDraggedEventHandler().handle(event);
                } else {
                    moveEventHandler.getMouseDraggedEventHandler().handle(event);
                }
            }

            changed = true;
        };
    }

}