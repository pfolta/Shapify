package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main;

import javafx.scene.Node;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.DocumentController;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.MainController;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.GUIState;

public class ContextMenu extends javafx.scene.control.ContextMenu {

    private MenuItem undoMenuItem;
    private MenuItem redoMenuItem;

    private MenuItem duplicateMenuItem;
    private MenuItem removeMenuItem;

    public ContextMenu(MainController mainController) {
        super();

        undoMenuItem = new MenuItem();
        undoMenuItem.setText("_Undo");
        undoMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN));
        undoMenuItem.setDisable(true);
        undoMenuItem.setOnAction(event1 -> {
            System.out.println("Undo Clicked!");
        });

        redoMenuItem = new MenuItem();
        redoMenuItem.setText("_Redo");
        redoMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN));
        redoMenuItem.setDisable(true);
        redoMenuItem.setOnAction(event1 -> {
            System.out.println("Redo Clicked!");
        });

        duplicateMenuItem = new MenuItem();
        duplicateMenuItem.setText("_Duplicate");
        duplicateMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.D, KeyCombination.CONTROL_DOWN));
        duplicateMenuItem.setOnAction(event -> {
            GUIState guiState = mainController.getGUIController().getGuiState();
            Node selectedObject = guiState.getSelectedObject();

            DocumentController documentController = mainController.getDocumentController();
            documentController.duplicateObject(selectedObject);
        });

        removeMenuItem = new MenuItem();
        removeMenuItem.setText("_Remove");
        removeMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.DELETE));
        removeMenuItem.setOnAction(event -> {
            GUIState guiState = mainController.getGUIController().getGuiState();

            mainController.getDocumentController().removeObject(guiState.getSelectedObject());
            guiState.setSelectedObject(null);
        });

        getItems().addAll(
            undoMenuItem,
            redoMenuItem,
            new SeparatorMenuItem(),
            duplicateMenuItem,
            removeMenuItem
        );
    }

}