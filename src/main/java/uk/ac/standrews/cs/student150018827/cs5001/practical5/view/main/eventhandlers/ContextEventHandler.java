package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.eventhandlers;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.ContextMenuEvent;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.MainController;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.GUIState;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.objects.Rectangle;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.ContextMenu;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.focusoutline.FocusOutline;

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