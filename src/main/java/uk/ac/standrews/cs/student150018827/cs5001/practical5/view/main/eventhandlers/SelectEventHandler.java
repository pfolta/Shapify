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
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.ArtBoard;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.focusoutline.FocusOutline;

public class SelectEventHandler extends MouseEventHandler {

    private int originalX;
    private int originalY;

    private int deltaX;
    private int deltaY;

    public SelectEventHandler(MainController mainController) {
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

                if (!(event.getSource() instanceof ArtBoard)) {
                    Node selectedObject = (Node) event.getSource();

                    if (selectedObject instanceof Rectangle) {
                        Rectangle rectangle = (Rectangle) selectedObject;

                        // Set selected object color
                        guiState.setCurrentForeground(((Color) rectangle.getFill()));
                    }

                    if (selectedObject instanceof Ellipse) {
                        Ellipse ellipse = (Ellipse) selectedObject;

                        // Set selected object color
                        guiState.setCurrentForeground(((Color) ellipse.getFill()));
                    }

                    if (selectedObject instanceof Line) {
                        Line line = (Line) selectedObject;

                        // Set selected object color
                        guiState.setCurrentForeground(((Color) line.getStroke()));
                    }

                    // Set selected object
                    guiState.setSelectedObject(selectedObject);

                    // Delegate event to Focus Outline for instant object move capabilities
                    if (!(event.getSource() instanceof Rectangle) || !((Rectangle) event.getSource()).isFocusOutline()) {
                        FocusOutline focusOutline = guiState.getFocusOutline();
                        Rectangle focusRectangle = focusOutline.getFocusRectangle();

                        focusRectangle.fireEvent(event);
                    }
                }
            }
        };
    }

    public EventHandler<MouseEvent> getMousePressedOutlineEventHandler() {
        return event -> {
            if (event.isPrimaryButtonDown()) {
                originalX = (int) event.getX();
                originalY = (int) event.getY();

                Rectangle rectangle = (Rectangle) event.getSource();

                deltaX = (int) (originalX - rectangle.getX());
                deltaY = (int) (originalY - rectangle.getY());
            }
        };
    }

    @Override
    public EventHandler<MouseEvent> getMouseDraggedEventHandler() {
        return event -> {
            if (event.isPrimaryButtonDown()) {
                // Delegate event to Focus Outline for instant object move capabilities
                if (!(event.getSource() instanceof Rectangle) || !((Rectangle) event.getSource()).isFocusOutline()) {
                    GUIState guiState = mainController.getGUIController().getGuiState();

                    FocusOutline focusOutline = guiState.getFocusOutline();
                    Rectangle focusRectangle = focusOutline.getFocusRectangle();

                    focusRectangle.fireEvent(event);

                    return;
                }

                int x = (int) event.getX();
                int y = (int) event.getY();

                int xpos = x - deltaX;
                int ypos = y - deltaY;

                Object object = event.getSource();

                Rectangle rectangle = (Rectangle) object;

                rectangle.setX(xpos);
                rectangle.setY(ypos);

                mainScene.getStatusBar().setCoordinatesLabel(x, y);
            }
        };
    }

    @Override
    public EventHandler<MouseEvent> getMouseReleasedEventHandler() {
        return null;
    }

}