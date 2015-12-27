package net.peterfolta.shapify.view.main.eventhandlers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import net.peterfolta.shapify.controller.MainController;
import net.peterfolta.shapify.model.GUIState;
import net.peterfolta.shapify.model.objects.Rectangle;
import net.peterfolta.shapify.view.main.focusoutline.FocusOutline;

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
            changed = false;

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
            changed = false;

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
                changed = true;
            }
        };
    }

}