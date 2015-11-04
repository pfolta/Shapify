package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.eventhandlers;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Ellipse;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.MainController;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.ArtBoard;

public class SelectEventHandler extends MouseEventHandler {

    private int originalX;
    private int originalY;

    public SelectEventHandler(MainController mainController) {
        super(mainController);
    }

    public EventHandler<MouseEvent> getMousePressedEventHandler() {
        return event -> {

        };
    }

    public EventHandler<MouseEvent> getMouseDraggedEventHandler() {
        return event -> {

        };
    }

    public EventHandler<MouseEvent> getMouseReleasedEventHandler() {
        return event -> {

        };
    }

}