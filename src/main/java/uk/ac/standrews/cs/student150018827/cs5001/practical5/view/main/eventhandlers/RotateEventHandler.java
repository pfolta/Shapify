package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.eventhandlers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.MainController;

public class RotateEventHandler extends MouseEventHandler {

    private int mouseOriginalX;

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
            mouseOriginalX = (int) event.getX();
        };
    }

    @Override
    public EventHandler<MouseEvent> getMouseDraggedEventHandler() {
        return event -> {
            int x = (int) event.getX();
            int angle = x - mouseOriginalX;

            document.rotateSelectedObject(angle);
        };
    }

    @Override
    public EventHandler<MouseEvent> getMouseReleasedEventHandler() {
        return null;
    }

}