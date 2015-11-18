package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.eventhandlers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.MainController;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.GUIState;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.objects.Rectangle;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.focusoutline.FocusOutline;

public class ShearEventHandler extends MouseEventHandler {

    private int originalX;

    public ShearEventHandler(MainController mainController) {
        super(mainController);
    }

    @Override
    public EventHandler<MouseEvent> getMouseExitedEventHandler() {
        return null;
    }

    @Override
    public EventHandler<MouseEvent> getMousePressedEventHandler() {
        return event -> {
            if (event.isPrimaryButtonDown() && event.isShiftDown()) {
                GUIState guiState = mainController.getGUIController().getGuiState();

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
            if (event.isPrimaryButtonDown()) {
                originalX = (int) event.getX();
            }
        };
    }

    @Override
    public EventHandler<MouseEvent> getMouseDraggedEventHandler() {
        return event -> {
            if (event.isPrimaryButtonDown()) {
                // Delegate event to Focus Outline for instant object shearing
                if (!(event.getSource() instanceof Rectangle) || !((Rectangle) event.getSource()).isFocusOutline()) {
                    GUIState guiState = mainController.getGUIController().getGuiState();

                    FocusOutline focusOutline = guiState.getFocusOutline();
                    Rectangle focusRectangle = focusOutline.getFocusRectangle();

                    focusRectangle.fireEvent(event);

                    return;
                }

                int x = (int) event.getX();
                double shearX = (x - originalX) / 1000.0;

                document.shearSelectedObject(shearX, 0);
            }
        };
    }

    @Override
    public EventHandler<MouseEvent> getMouseReleasedEventHandler() {
        return null;
    }

}