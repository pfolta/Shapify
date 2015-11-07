package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.DocumentController;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.MainController;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.GUIState;

public class MenuBar extends javafx.scene.control.MenuBar {

    private MainController mainController;

    private MenuItem fileNewItem;
    private MenuItem fileOpenItem;
    private MenuItem fileSaveItem;
    private MenuItem fileSaveAsItem;
    private MenuItem fileExportItem;
    private MenuItem fileCloseItem;
    private MenuItem fileExitItem;

    private MenuItem editUndoItem;
    private MenuItem editRedoItem;
    private MenuItem editDuplicateMenuItem;
    private MenuItem editRemoveMenuItem;
    private MenuItem editMoveBottomMenuItem;
    private MenuItem editMoveDownMenuItem;
    private MenuItem editMoveUpMenuItem;
    private MenuItem editMoveTopMenuItem;
    private MenuItem editDeselectMenuItem;

    private CustomMenuItem toolColorPickerItem;

    private CheckMenuItem viewMenuBarItem;
    private CheckMenuItem viewToolBarItem;
    private CheckMenuItem viewStatusBarItem;
    private CheckMenuItem viewFullscreenItem;

    private MenuItem helpAboutItem;

    public MenuBar(MainController mainController) {
        super();

        this.mainController = mainController;

        Menu fileMenu = buildFileMenu();
        Menu editMenu = buildEditMenu();
        Menu toolMenu = buildToolMenu();
        Menu viewMenu = buildViewMenu();
        Menu helpMenu = buildHelpMenu();

        this.getMenus().addAll(
            fileMenu,
            editMenu,
            toolMenu,
            viewMenu,
            helpMenu
        );

        activateControls(false);
    }

    private Menu buildFileMenu() {
        Menu menu = new Menu();
        menu.setText("_File");

        fileNewItem = new MenuItem();
        fileNewItem.setText("_New");
        fileNewItem.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN));
        fileNewItem.setOnAction(event -> {
            if (mainController.getDocumentController().closeDocument()) {
                mainController.getGUIController().openNewDrawingDialog((Stage) MenuBar.this.getScene().getWindow());
            }
        });

        fileOpenItem = new MenuItem();
        fileOpenItem.setText("_Open...");
        fileOpenItem.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));
        fileOpenItem.setOnAction(event -> {
            if (mainController.getDocumentController().closeDocument()) {
                mainController.getGUIController().getMainWindow().openFile();
            }
        });

        fileSaveItem = new MenuItem();
        fileSaveItem.setText("_Save");
        fileSaveItem.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
        fileSaveItem.setOnAction(event -> mainController.getGUIController().getMainWindow().saveFile());

        fileSaveAsItem = new MenuItem();
        fileSaveAsItem.setText("Save _As...");
        fileSaveAsItem.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN));
        fileSaveAsItem.setOnAction(event -> mainController.getGUIController().getMainWindow().saveAsFile());

        fileExportItem = new MenuItem();
        fileExportItem.setText("_Export...");
        fileExportItem.setOnAction(event -> System.out.println("Export Clicked!"));

        fileCloseItem = new MenuItem();
        fileCloseItem.setText("_Close");
        fileCloseItem.setAccelerator(new KeyCodeCombination(KeyCode.W, KeyCombination.CONTROL_DOWN));
        fileCloseItem.setOnAction(event -> mainController.getDocumentController().closeDocument());

        fileExitItem = new MenuItem();
        fileExitItem.setText("E_xit");
        fileExitItem.setAccelerator(new KeyCodeCombination(KeyCode.F4, KeyCombination.ALT_DOWN));
        fileExitItem.setOnAction(event -> mainController.exit());

        menu.getItems().addAll(
            fileNewItem,
            new SeparatorMenuItem(),
            fileOpenItem,
            fileSaveItem,
            fileSaveAsItem,
            new SeparatorMenuItem(),
            fileExportItem,
            new SeparatorMenuItem(),
            fileCloseItem,
            fileExitItem
        );

        return menu;
    }

    private Menu buildEditMenu() {
        Menu menu = new Menu();
        menu.setText("_Edit");

        editUndoItem = new MenuItem();
        editUndoItem.setText("_Undo");
        editUndoItem.setAccelerator(new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN));
        editUndoItem.setOnAction(event -> System.out.println("Undo Clicked!"));

        editRedoItem = new MenuItem();
        editRedoItem.setText("_Redo");
        editRedoItem.setAccelerator(new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN));
        editRedoItem.setOnAction(event -> System.out.println("Redo Clicked!"));

        editDuplicateMenuItem = new MenuItem();
        editDuplicateMenuItem.setText("_Duplicate");
        editDuplicateMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.D, KeyCombination.CONTROL_DOWN));
        editDuplicateMenuItem.setDisable(true);
        editDuplicateMenuItem.setOnAction(event -> {
            GUIState guiState = mainController.getGUIController().getGuiState();
            Node selectedObject = guiState.getSelectedObject();

            DocumentController documentController = mainController.getDocumentController();
            documentController.duplicateObject(selectedObject);
        });

        editRemoveMenuItem = new MenuItem();
        editRemoveMenuItem.setText("_Remove");
        editRemoveMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.DELETE));
        editRemoveMenuItem.setDisable(true);
        editRemoveMenuItem.setOnAction(event -> {
            GUIState guiState = mainController.getGUIController().getGuiState();

            mainController.getDocumentController().removeObject(guiState.getSelectedObject());
            guiState.setSelectedObject(null);
        });

        editMoveBottomMenuItem = new MenuItem();
        editMoveBottomMenuItem.setText("Move to Bottom");
        editMoveBottomMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.END));
        editMoveBottomMenuItem.setDisable(true);
        editMoveBottomMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            }
        });

        editMoveDownMenuItem = new MenuItem();
        editMoveDownMenuItem.setText("Move Down");
        editMoveDownMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.PAGE_DOWN));
        editMoveDownMenuItem.setDisable(true);
        editMoveDownMenuItem.setOnAction(event -> {
            DocumentController documentController = mainController.getDocumentController();
            GUIState guiState = mainController.getGUIController().getGuiState();

            documentController.moveObjectDown(guiState.getSelectedObject());
        });

        editMoveUpMenuItem = new MenuItem();
        editMoveUpMenuItem.setText("Move Up");
        editMoveUpMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.PAGE_UP));
        editMoveUpMenuItem.setDisable(true);
        editMoveUpMenuItem.setOnAction(event -> {
            DocumentController documentController = mainController.getDocumentController();
            GUIState guiState = mainController.getGUIController().getGuiState();

            documentController.moveObjectUp(guiState.getSelectedObject());
        });

        editMoveTopMenuItem = new MenuItem();
        editMoveTopMenuItem.setText("Move to Top");
        editMoveTopMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.HOME));
        editMoveTopMenuItem.setDisable(true);
        editMoveTopMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            }
        });

        editDeselectMenuItem = new MenuItem();
        editDeselectMenuItem.setText("_Deselect");
        editDeselectMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.ESCAPE));
        editDeselectMenuItem.setDisable(true);
        editDeselectMenuItem.setOnAction(event -> {
            GUIState guiState = mainController.getGUIController().getGuiState();

            guiState.setSelectedObject(null);
        });

        menu.getItems().addAll(
            editUndoItem,
            editRedoItem,
            new SeparatorMenuItem(),
            editDuplicateMenuItem,
            editRemoveMenuItem,
            new SeparatorMenuItem(),
                editMoveBottomMenuItem,
                editMoveDownMenuItem,
                editMoveUpMenuItem,
                editMoveTopMenuItem,
            new SeparatorMenuItem(),
            editDeselectMenuItem
        );

        return menu;
    }

    private Menu buildToolMenu() {
        Menu menu = new Menu();
        menu.setText("_Tool");

        ColorPicker colorPicker = new ColorPicker();

        toolColorPickerItem = new CustomMenuItem(colorPicker);
        toolColorPickerItem.setHideOnClick(false);

        menu.getItems().addAll(
            toolColorPickerItem
        );

        return menu;
    }

    private Menu buildViewMenu() {
        Menu menu = new Menu();
        menu.setText("_View");

        viewMenuBarItem = new CheckMenuItem();
        viewMenuBarItem.setText("_Menu Bar");
        viewMenuBarItem.setSelected(true);
        viewMenuBarItem.setOnAction(event -> mainController.getGUIController().getMainWindow().getMainScene().showMenuBar(viewMenuBarItem.isSelected()));

        viewToolBarItem = new CheckMenuItem();
        viewToolBarItem.setText("_Toolbar");
        viewToolBarItem.setSelected(true);
        viewToolBarItem.setOnAction(event -> mainController.getGUIController().getMainWindow().getMainScene().showToolBar(viewToolBarItem.isSelected()));

        viewStatusBarItem = new CheckMenuItem();
        viewStatusBarItem.setText("_Status Bar");
        viewStatusBarItem.setSelected(true);
        viewStatusBarItem.setOnAction(event -> mainController.getGUIController().getMainWindow().getMainScene().showStatusBar(viewStatusBarItem.isSelected()));

        viewFullscreenItem = new CheckMenuItem();
        viewFullscreenItem.setText("_Fullscreen");
        viewFullscreenItem.setAccelerator(new KeyCodeCombination(KeyCode.F11));
        viewFullscreenItem.setOnAction(event -> mainController.getGUIController().getMainWindow().setFullscreen(viewFullscreenItem.isSelected()));

        menu.getItems().addAll(
            viewMenuBarItem,
            viewToolBarItem,
            viewStatusBarItem,
            new SeparatorMenuItem(),
            viewFullscreenItem
        );

        return menu;
    }

    private Menu buildHelpMenu() {
        Menu menu = new Menu();
        menu.setText("_Help");

        helpAboutItem = new MenuItem();
        helpAboutItem.setText("_About");
        helpAboutItem.setOnAction(event -> mainController.getGUIController().openAboutDialog((Stage) MenuBar.this.getScene().getWindow()));

        menu.getItems().addAll(helpAboutItem);

        return menu;
    }

    public void selectViewMenuBarItem(boolean selected) {
        viewMenuBarItem.setSelected(selected);
    }

    public void selectViewToolBarItem(boolean selected) {
        viewToolBarItem.setSelected(selected);
    }

    public void selectViewStatusBarItem(boolean selected) {
        viewStatusBarItem.setSelected(selected);
    }

    public void activateControls(boolean activate) {
        fileSaveItem.setDisable(!activate);
        fileSaveAsItem.setDisable(!activate);
        fileExportItem.setDisable(!activate);
        fileCloseItem.setDisable(!activate);
    }

    public void objectSelected(boolean selected) {
        editDuplicateMenuItem.setDisable(!selected);
        editRemoveMenuItem.setDisable(!selected);
        editMoveBottomMenuItem.setDisable(!selected);
        editMoveDownMenuItem.setDisable(!selected);
        editMoveUpMenuItem.setDisable(!selected);
        editMoveTopMenuItem.setDisable(!selected);
        editDeselectMenuItem.setDisable(!selected);
    }

}