package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.MainController;

public class MenuBar extends javafx.scene.control.MenuBar {

    private MainController mainController;
    private MainWindow mainWindow;

    private MenuItem fileNewItem;
    private MenuItem fileOpenItem;
    private MenuItem fileSaveItem;
    private MenuItem fileSaveAsItem;
    private MenuItem fileExportItem;
    private MenuItem fileCloseItem;
    private MenuItem fileExitItem;

    private MenuItem editUndoItem;
    private MenuItem editRedoItem;

    private CheckMenuItem viewToolBarItem;
    private CheckMenuItem viewStatusBarItem;
    private CheckMenuItem viewFullscreenItem;

    private MenuItem helpAboutItem;

    public MenuBar(MainController mainController, MainWindow mainWindow) {
        super();

        this.mainController = mainController;
        this.mainWindow = mainWindow;

        Menu fileMenu = buildFileMenu();
        Menu editMenu = buildEditMenu();
        Menu viewMenu = buildViewMenu();
        Menu helpMenu = buildHelpMenu();

        this.getMenus().addAll(
            fileMenu,
            editMenu,
            viewMenu,
            helpMenu
        );
    }

    private Menu buildFileMenu() {
        Menu menu = new Menu();
        menu.setText("_File");

        fileNewItem = new MenuItem();
        fileNewItem.setText("_New");
        fileNewItem.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN));
        fileNewItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainController.getGUIController().openNewDrawingDialog((Stage) MenuBar.this.getScene().getWindow());
            }
        });

        fileOpenItem = new MenuItem();
        fileOpenItem.setText("_Open...");
        fileOpenItem.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));
        fileOpenItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainWindow.openFile();
            }
        });

        fileSaveItem = new MenuItem();
        fileSaveItem.setText("_Save");
        fileSaveItem.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
        fileSaveItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainWindow.saveFile();
            }
        });

        fileSaveAsItem = new MenuItem();
        fileSaveAsItem.setText("Save _As...");
        fileSaveAsItem.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN));
        fileSaveAsItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainWindow.saveAsFile();
            }
        });

        fileExportItem = new MenuItem();
        fileExportItem.setText("_Export...");
        fileExportItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Export Clicked!");
            }
        });

        fileCloseItem = new MenuItem();
        fileCloseItem.setText("_Close");
        fileCloseItem.setAccelerator(new KeyCodeCombination(KeyCode.W, KeyCombination.CONTROL_DOWN));
        fileCloseItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Close Clicked!");
            }
        });

        fileExitItem = new MenuItem();
        fileExitItem.setText("E_xit");
        fileExitItem.setAccelerator(new KeyCodeCombination(KeyCode.F4, KeyCombination.ALT_DOWN));
        fileExitItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainController.exit();
            }
        });

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
        editUndoItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Undo Clicked!");
            }
        });

        editRedoItem = new MenuItem();
        editRedoItem.setText("_Redo");
        editRedoItem.setAccelerator(new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN));
        editRedoItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Redo Clicked!");
            }
        });

        menu.getItems().addAll(editUndoItem, editRedoItem);

        return menu;
    }

    private Menu buildViewMenu() {
        Menu menu = new Menu();
        menu.setText("_View");

        viewToolBarItem = new CheckMenuItem();
        viewToolBarItem.setText("_Toolbar");
        viewToolBarItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Toolbar Clicked!");
            }
        });

        viewStatusBarItem = new CheckMenuItem();
        viewStatusBarItem.setText("_Status Bar");
        viewStatusBarItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Status Bar Clicked!");
            }
        });

        viewFullscreenItem = new CheckMenuItem();
        viewFullscreenItem.setText("_Fullscreen");
        viewFullscreenItem.setAccelerator(new KeyCodeCombination(KeyCode.F11));
        viewFullscreenItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainWindow.setFullscreen(viewFullscreenItem.isSelected());
            }
        });

        menu.getItems().addAll(
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
        helpAboutItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainController.getGUIController().openAboutDialog((Stage) MenuBar.this.getScene().getWindow());
            }
        });

        menu.getItems().addAll(helpAboutItem);

        return menu;
    }

}