package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.eventhandlers;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.DocumentController;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.MainController;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.GUIState;

public class KeyEventHandler {

    private MainController mainController;

    public KeyEventHandler(MainController mainController) {
        this.mainController = mainController;
    }

    public EventHandler<KeyEvent> getKeyPressedEventHandler() {
        return event -> {
            DocumentController documentController = mainController.getDocumentController();
            GUIState guiState = mainController.getGUIController().getGuiState();

            if (event.getCode().equals(KeyCode.ALT)) {
                mainController.getGUIController().getMainWindow().getMainScene().showMenuBar(true);
            }

            if (guiState.getSelectedObject() != null) {
                switch (event.getCode()) {
                    case DELETE: {
                        documentController.removeObject(guiState.getSelectedObject());
                        guiState.setSelectedObject(null);

                        break;
                    }
                }
            }
        };
    }

}
