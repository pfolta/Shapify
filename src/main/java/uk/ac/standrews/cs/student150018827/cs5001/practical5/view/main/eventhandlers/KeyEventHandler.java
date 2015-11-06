package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.eventhandlers;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.MainController;

public class KeyEventHandler {

    private MainController mainController;

    public KeyEventHandler(MainController mainController) {
        this.mainController = mainController;
    }

    public EventHandler<KeyEvent> getKeyPressedEventHandler() {
        return event -> {
            if (event.getCode().equals(KeyCode.ALT)) {
                mainController.getGUIController().getMainWindow().getMainScene().showMenuBar(true);
            }
        };
    }

}