package net.peterfolta.shapify.view.main.eventhandlers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import net.peterfolta.shapify.controller.MainController;

public class RotateEventHandler extends MouseEventHandler {

    private int originalX;

    public RotateEventHandler(MainController mainController) {
        super(mainController);
    }

    @Override
    public EventHandler<MouseEvent> getMouseExitedEventHandler() {
        return null;
    }

    @Override
    public EventHandler<MouseEvent> getMousePressedEventHandler() {
        return event -> {
            originalX = (int) event.getX();

            changed = false;
        };
    }

    @Override
    public EventHandler<MouseEvent> getMouseDraggedEventHandler() {
        return event -> {
            super.getMouseDraggedEventHandler().handle(event);

            int x = (int) event.getX();
            int angle = x - originalX;

            document.rotateSelectedObject(angle, false);
            changed = true;
        };
    }

}