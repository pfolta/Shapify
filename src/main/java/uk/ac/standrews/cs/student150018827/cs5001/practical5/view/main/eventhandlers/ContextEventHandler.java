package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.eventhandlers;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.ContextMenuEvent;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.MainController;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.GUIState;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.ContextMenu;

public class ContextEventHandler implements EventHandler<ContextMenuEvent> {

    private MainController mainController;

    private ContextMenu contextMenu;

    public ContextEventHandler(MainController mainController) {
        this.mainController = mainController;

        contextMenu = new ContextMenu(mainController);
    }

    @Override
    public void handle(ContextMenuEvent event) {
        GUIState guiState = mainController.getGUIController().getGuiState();

        guiState.setSelectedObject((Node) event.getSource());

        contextMenu.show(guiState.getSelectedObject(), event.getScreenX(), event.getScreenY());
    }

}