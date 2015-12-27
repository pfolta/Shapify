package net.peterfolta.shapify.view.main.eventhandlers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import net.peterfolta.shapify.controller.HistoryController;
import net.peterfolta.shapify.controller.MainController;
import net.peterfolta.shapify.model.Document;
import net.peterfolta.shapify.view.main.ArtBoard;
import net.peterfolta.shapify.view.main.MainScene;

public abstract class MouseEventHandler {

    protected MainController mainController;

    protected MainScene mainScene;
    protected Document document;

    protected boolean changed;

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

    public abstract EventHandler<MouseEvent> getMouseExitedEventHandler();

    public abstract EventHandler<MouseEvent> getMousePressedEventHandler();

    public EventHandler<MouseEvent> getMouseDraggedEventHandler() {
        return event -> {
            int x = (int) event.getX();
            int y = (int) event.getY();

            mainScene.getStatusBar().setCoordinatesLabel(x, y);
        };
    }

    public EventHandler<MouseEvent> getMouseReleasedEventHandler() {
        return event -> {
            if (!(event.getSource() instanceof ArtBoard)) {
                if (changed) {
                    // Create History Point
                    HistoryController.getInstance(mainController).createHistoryPoint();
                }
            }
        };
    }

}