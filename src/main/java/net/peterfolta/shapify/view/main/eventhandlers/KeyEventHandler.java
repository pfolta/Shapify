package net.peterfolta.shapify.view.main.eventhandlers;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import net.peterfolta.shapify.controller.MainController;

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