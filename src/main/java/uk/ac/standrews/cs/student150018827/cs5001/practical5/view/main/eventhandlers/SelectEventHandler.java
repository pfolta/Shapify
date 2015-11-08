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

    public EventHandler<MouseEvent> getMousePressedEventHandler() {
        return event -> {
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
        };
    }

    public EventHandler<MouseEvent> getMousePressedOutlineEventHandler() {
        return event -> {
            originalX = (int) event.getX();
            originalY = (int) event.getY();

            Rectangle rectangle = (Rectangle) event.getSource();

            deltaX = (int) (originalX - rectangle.getX());
            deltaY = (int) (originalY - rectangle.getY());
        };
    }

    public EventHandler<MouseEvent> getMouseDraggedEventHandler() {
        return event -> {
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

            ArtBoard artBoard = mainScene.getArtBoard();

            int xpos = x - deltaX;
            int ypos = y - deltaY;

            Object object = event.getSource();

            Rectangle rectangle = (Rectangle) object;

            // Ensure event is within artboard boundaries
            xpos = Math.max(xpos, 0);
            xpos = Math.min(xpos, (int) (artBoard.getWidth() - rectangle.getWidth()));

            ypos = Math.max(ypos, 0);
            ypos = Math.min(ypos, (int) (artBoard.getHeight() - rectangle.getHeight()));

            rectangle.setX(xpos);
            rectangle.setY(ypos);

            // Ensure event is within artboard boundaries for coordinates display
            x = Math.max(x, 0);
            x = Math.min(x, (int) artBoard.getWidth());

            y = Math.max(y, 0);
            y = Math.min(y, (int) artBoard.getHeight());

            mainScene.getStatusBar().setCoordinatesLabel(x, y);
        };
    }

    public EventHandler<MouseEvent> getMouseReleasedEventHandler() {
        return event -> {
        };
    }

}