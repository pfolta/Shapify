package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.eventhandlers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.MainController;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.Document;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.MainScene;

public abstract class MouseEventHandler {

    protected MainController mainController;

    protected MainScene mainScene;
    protected Document document;

    public MouseEventHandler(MainController mainController) {
        this.mainController = mainController;

        mainScene = mainController.getGUIController().getMainWindow().getMainScene();
        document = mainController.getDocumentController().getDocument();
    }

    public EventHandler<MouseEvent> getMouseMovedEventHandler() {
        return event -> {
            int x = (int) event.getX();
            int y = (int) event.getY();

            mainScene.getStatusBar().setCoordinatesLabel(x, y);
        };
    }

    public EventHandler<MouseEvent> getMouseExitedEventHandler() {
        return event -> {
            mainScene.getStatusBar().clearCoordinatesLabel();
        };
    }

    public abstract EventHandler<MouseEvent> getMousePressedEventHandler();

    public abstract EventHandler<MouseEvent> getMouseDraggedEventHandler();

    public abstract EventHandler<MouseEvent> getMouseReleasedEventHandler();

}