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

    private MenuItem moveToBottomMenuItem;
    private MenuItem moveDownMenuItem;
    private MenuItem moveUpMenuItem;
    private MenuItem moveToTopMenuItem;

    private MenuItem deselectMenuItem;

    public ContextMenu(MainController mainController) {
        super();

        undoMenuItem = new MenuItem();
        undoMenuItem.setText("_Undo");
        undoMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN));
        undoMenuItem.setDisable(true);
        undoMenuItem.setOnAction(event1 -> System.out.println("Undo Clicked!"));

        redoMenuItem = new MenuItem();
        redoMenuItem.setText("_Redo");
        redoMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN));
        redoMenuItem.setDisable(true);
        redoMenuItem.setOnAction(event1 -> System.out.println("Redo Clicked!"));

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

        moveToBottomMenuItem = new MenuItem();
        moveToBottomMenuItem.setText("Move to Bottom");
        moveToBottomMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.END));
        moveToBottomMenuItem.setOnAction(event -> {
            DocumentController documentController = mainController.getDocumentController();
            GUIState guiState = mainController.getGUIController().getGuiState();

            documentController.moveObjectToBottom(guiState.getSelectedObject());
        });

        moveDownMenuItem = new MenuItem();
        moveDownMenuItem.setText("Move Down");
        moveDownMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.PAGE_DOWN));
        moveDownMenuItem.setOnAction(event -> {
            DocumentController documentController = mainController.getDocumentController();
            GUIState guiState = mainController.getGUIController().getGuiState();

            documentController.moveObjectDown(guiState.getSelectedObject());
        });

        moveUpMenuItem = new MenuItem();
        moveUpMenuItem.setText("Move Up");
        moveUpMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.PAGE_UP));
        moveUpMenuItem.setOnAction(event -> {
            DocumentController documentController = mainController.getDocumentController();
            GUIState guiState = mainController.getGUIController().getGuiState();

            documentController.moveObjectUp(guiState.getSelectedObject());
        });

        moveToTopMenuItem = new MenuItem();
        moveToTopMenuItem.setText("Move to Top");
        moveToTopMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.HOME));
        moveToTopMenuItem.setOnAction(event -> {
            DocumentController documentController = mainController.getDocumentController();
            GUIState guiState = mainController.getGUIController().getGuiState();

            documentController.moveObjectToTop(guiState.getSelectedObject());
        });

        deselectMenuItem = new MenuItem();
        deselectMenuItem.setText("_Deselect");
        deselectMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.ESCAPE));
        deselectMenuItem.setOnAction(event -> {
            GUIState guiState = mainController.getGUIController().getGuiState();

            guiState.setSelectedObject(null);
        });

        getItems().addAll(
            undoMenuItem,
            redoMenuItem,
            new SeparatorMenuItem(),
            duplicateMenuItem,
            removeMenuItem,
            new SeparatorMenuItem(),
            moveToBottomMenuItem,
            moveDownMenuItem,
            moveUpMenuItem,
            moveToTopMenuItem,
            new SeparatorMenuItem(),
            deselectMenuItem
        );
    }

}