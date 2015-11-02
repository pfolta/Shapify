package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.GUIController;

public class MainMenuBar extends MenuBar {

    private GUIController guiController;

    private MenuItem fileNewItem;
    private MenuItem fileOpenItem;
    private MenuItem fileSaveItem;
    private MenuItem fileSaveAsItem;
    private MenuItem fileExitItem;

    private MenuItem helpAboutItem;

    public MainMenuBar(GUIController guiController) {
        super();

        this.guiController = guiController;

        Menu fileMenu = buildFileMenu();
        Menu helpMenu = buildHelpMenu();

        this.getMenus().addAll(fileMenu, helpMenu);
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
                System.out.println("New Clicked!");
            }
        });

        fileOpenItem = new MenuItem();
        fileOpenItem.setText("_Open");
        fileOpenItem.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));
        fileOpenItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Open Clicked!");
            }
        });

        fileSaveItem = new MenuItem();
        fileSaveItem.setText("_Save");
        fileSaveItem.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
        fileSaveItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Save Clicked!");
            }
        });

        fileSaveAsItem = new MenuItem();
        fileSaveAsItem.setText("Save _As");
        fileSaveAsItem.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN));
        fileSaveAsItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Save As Clicked!");
            }
        });

        fileExitItem = new MenuItem();
        fileExitItem.setText("E_xit");
        fileExitItem.setAccelerator(new KeyCodeCombination(KeyCode.F4, KeyCombination.ALT_DOWN));
        fileExitItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                guiController.exit();
            }
        });

        menu.getItems().addAll(fileNewItem, new SeparatorMenuItem(), fileOpenItem, fileSaveItem, fileSaveAsItem, new SeparatorMenuItem(), fileExitItem);

        return menu;
    }

    private Menu buildHelpMenu() {
        Menu menu = new Menu();
        menu.setText("_Help");

        helpAboutItem = new MenuItem();
        helpAboutItem.setText("About");
        helpAboutItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                guiController.openAboutDialog((Stage) MainMenuBar.this.getScene().getWindow());
            }
        });

        menu.getItems().addAll(helpAboutItem);

        return menu;
    }

}