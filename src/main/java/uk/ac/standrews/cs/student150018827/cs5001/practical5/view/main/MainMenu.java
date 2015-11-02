package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.GUIController;

public class MainMenu extends MenuBar {

    private GUIController guiController;

    private MenuItem fileExitItem;

    private MenuItem helpAboutItem;

    public MainMenu(GUIController guiController) {
        this.guiController = guiController;

        Menu fileMenu = buildFileMenu();
        Menu helpMenu = buildHelpMenu();

        this.getMenus().addAll(fileMenu, helpMenu);
    }

    private Menu buildFileMenu() {
        Menu fileMenu = new Menu();
        fileMenu.setText("_File");

        fileExitItem = new MenuItem();
        fileExitItem.setText("E_xit");
        fileExitItem.setAccelerator(new KeyCodeCombination(KeyCode.F4, KeyCombination.ALT_DOWN));
        fileExitItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                guiController.exit();
            }
        });

        fileMenu.getItems().addAll(fileExitItem);

        return fileMenu;
    }

    private Menu buildHelpMenu() {
        Menu helpMenu = new Menu();
        helpMenu.setText("_Help");

        helpAboutItem = new MenuItem();
        helpAboutItem.setText("About");
        helpAboutItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                guiController.openAboutDialog((Stage) MainMenu.this.getScene().getWindow());
            }
        });

        helpMenu.getItems().addAll(helpAboutItem);

        return helpMenu;
    }

}