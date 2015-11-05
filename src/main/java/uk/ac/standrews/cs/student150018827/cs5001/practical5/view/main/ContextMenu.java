package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main;

import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.MainController;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.GUIState;

public class ContextMenu extends javafx.scene.control.ContextMenu {

    private MainController mainController;

    private MenuItem removeMenuItem;

    public ContextMenu(MainController mainController) {
        super();

        this.mainController = mainController;

        removeMenuItem = new MenuItem();
        removeMenuItem.setText("_Remove");
        removeMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.DELETE));
        removeMenuItem.setOnAction(event -> {
            GUIState guiState = ContextMenu.this.mainController.getGUIController().getGuiState();

            ContextMenu.this.mainController.getDocumentController().removeObject(guiState.getSelectedObject());
            guiState.setSelectedObject(null);
        });

        getItems().addAll(removeMenuItem);
    }

}