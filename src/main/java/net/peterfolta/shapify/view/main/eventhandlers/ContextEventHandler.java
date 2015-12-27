package net.peterfolta.shapify.view.main.eventhandlers;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.ContextMenuEvent;
import net.peterfolta.shapify.controller.MainController;
import net.peterfolta.shapify.model.GUIState;
import net.peterfolta.shapify.model.objects.Rectangle;
import net.peterfolta.shapify.view.main.ContextMenu;
import net.peterfolta.shapify.view.main.focusoutline.FocusOutline;

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

        if (!(event.getSource() instanceof Rectangle) || !((Rectangle) event.getSource()).isFocusOutline()) {
            if (event.getSource() instanceof Node) {
                guiState.setSelectedObject((Node) event.getSource());
            }

            FocusOutline focusOutline = guiState.getFocusOutline();
            Rectangle focusRectangle = focusOutline.getFocusRectangle();

            focusRectangle.fireEvent(event);
        }

        contextMenu.show(guiState.getSelectedObject(), event.getScreenX(), event.getScreenY());
    }

}