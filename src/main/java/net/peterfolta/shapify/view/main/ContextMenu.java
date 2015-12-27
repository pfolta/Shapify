package net.peterfolta.shapify.view.main;

import javafx.scene.Node;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import net.peterfolta.shapify.controller.DocumentController;
import net.peterfolta.shapify.controller.HistoryController;
import net.peterfolta.shapify.controller.MainController;
import net.peterfolta.shapify.model.GUIState;

public class ContextMenu extends javafx.scene.control.ContextMenu {

    private MainController mainController;

    private MenuItem undoMenuItem;
    private MenuItem redoMenuItem;

    private MenuItem duplicateMenuItem;
    private MenuItem removeMenuItem;

    private MenuItem moveToBottomMenuItem;
    private MenuItem moveDownMenuItem;
    private MenuItem moveUpMenuItem;
    private MenuItem moveToTopMenuItem;
    private MenuItem rotate90DegRightMenuItem;
    private MenuItem rotate90DegLeftMenuItem;

    private MenuItem deselectMenuItem;

    public ContextMenu(MainController mainController) {
        super();

        this.mainController = mainController;

        undoMenuItem = new MenuItem();
        undoMenuItem.setText("_Undo");
        undoMenuItem.setGraphic(new ImageView(new Image(ClassLoader.getSystemResourceAsStream("icons/16x16/undo.png"))));
        undoMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN));
        undoMenuItem.setOnAction(event -> HistoryController.getInstance(mainController).undo());

        redoMenuItem = new MenuItem();
        redoMenuItem.setText("_Redo");
        redoMenuItem.setGraphic(new ImageView(new Image(ClassLoader.getSystemResourceAsStream("icons/16x16/redo.png"))));
        redoMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN));
        redoMenuItem.setOnAction(event -> HistoryController.getInstance(mainController).redo());

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
        removeMenuItem.setGraphic(new ImageView(new Image(ClassLoader.getSystemResourceAsStream("icons/16x16/delete.png"))));
        removeMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.DELETE));
        removeMenuItem.setOnAction(event -> {
            GUIState guiState = mainController.getGUIController().getGuiState();

            mainController.getDocumentController().removeObject(guiState.getSelectedObject());
            guiState.setSelectedObject(null);
        });

        moveToBottomMenuItem = new MenuItem();
        moveToBottomMenuItem.setText("Move to Bottom");
        moveToBottomMenuItem.setGraphic(new ImageView(new Image(ClassLoader.getSystemResourceAsStream("icons/16x16/shape_move_back.png"))));
        moveToBottomMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.END));
        moveToBottomMenuItem.setOnAction(event -> {
            DocumentController documentController = mainController.getDocumentController();
            GUIState guiState = mainController.getGUIController().getGuiState();

            documentController.moveObjectToBottom(guiState.getSelectedObject());
        });

        moveDownMenuItem = new MenuItem();
        moveDownMenuItem.setText("Move Down");
        moveDownMenuItem.setGraphic(new ImageView(new Image(ClassLoader.getSystemResourceAsStream("icons/16x16/shape_move_backwards.png"))));
        moveDownMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.PAGE_DOWN));
        moveDownMenuItem.setOnAction(event -> {
            DocumentController documentController = mainController.getDocumentController();
            GUIState guiState = mainController.getGUIController().getGuiState();

            documentController.moveObjectDown(guiState.getSelectedObject());
        });

        moveUpMenuItem = new MenuItem();
        moveUpMenuItem.setText("Move Up");
        moveUpMenuItem.setGraphic(new ImageView(new Image(ClassLoader.getSystemResourceAsStream("icons/16x16/shape_move_forwards.png"))));
        moveUpMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.PAGE_UP));
        moveUpMenuItem.setOnAction(event -> {
            DocumentController documentController = mainController.getDocumentController();
            GUIState guiState = mainController.getGUIController().getGuiState();

            documentController.moveObjectUp(guiState.getSelectedObject());
        });

        moveToTopMenuItem = new MenuItem();
        moveToTopMenuItem.setText("Move to Top");
        moveToTopMenuItem.setGraphic(new ImageView(new Image(ClassLoader.getSystemResourceAsStream("icons/16x16/shape_move_front.png"))));
        moveToTopMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.HOME));
        moveToTopMenuItem.setOnAction(event -> {
            DocumentController documentController = mainController.getDocumentController();
            GUIState guiState = mainController.getGUIController().getGuiState();

            documentController.moveObjectToTop(guiState.getSelectedObject());
        });

        rotate90DegRightMenuItem = new MenuItem();
        rotate90DegRightMenuItem.setText("Rotate 90° _Right");
        rotate90DegRightMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.RIGHT, KeyCombination.CONTROL_DOWN));
        rotate90DegRightMenuItem.setGraphic(new ImageView(new Image(ClassLoader.getSystemResourceAsStream("icons/16x16/shape_rotate_clockwise.png"))));
        rotate90DegRightMenuItem.setOnAction(event -> mainController.getDocumentController().getDocument().rotateSelectedObject(90.0, true));

        rotate90DegLeftMenuItem = new MenuItem();
        rotate90DegLeftMenuItem.setText("Rotate 90° _Left");
        rotate90DegLeftMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.LEFT, KeyCombination.CONTROL_DOWN));
        rotate90DegLeftMenuItem.setGraphic(new ImageView(new Image(ClassLoader.getSystemResourceAsStream("icons/16x16/shape_rotate_anticlockwise.png"))));
        rotate90DegLeftMenuItem.setOnAction(event -> mainController.getDocumentController().getDocument().rotateSelectedObject(-90.0, true));

        deselectMenuItem = new MenuItem();
        deselectMenuItem.setText("_Deselect");
        deselectMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.ESCAPE));
        deselectMenuItem.setOnAction(event -> mainController.getGUIController().getGuiState().setSelectedObject(null));

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
            rotate90DegRightMenuItem,
            rotate90DegLeftMenuItem,
            new SeparatorMenuItem(),
            deselectMenuItem
        );
    }

    @Override
    public void show() {
        super.show();

        undoMenuItem.setDisable(!HistoryController.getInstance(mainController).isUndoAvailable());
        redoMenuItem.setDisable(!HistoryController.getInstance(mainController).isRedoAvailable());
    }

}