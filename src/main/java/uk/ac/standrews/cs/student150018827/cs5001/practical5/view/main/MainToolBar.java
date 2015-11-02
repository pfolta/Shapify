package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.GUIController;

public class MainToolBar extends ToolBar {

    private GUIController guiController;

    private Button btnNew;
    private Button btnOpen;
    private Button btnSave;

    private Button btnUndo;
    private Button btnRedo;

    private ColorPicker btnColorPicker;

    public MainToolBar(GUIController guiController) {
        super();

        this.guiController = guiController;

        buildToolBar();
    }

    private void buildToolBar() {
        buildFileControls();
        getItems().add(new Separator());
        buildEditControls();
        getItems().add(new Separator());
        buildColorControls();
    }

    private void buildFileControls() {
        btnNew = new Button();
        btnNew.setText("New");
        btnNew.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("New Clicked!");
            }
        });

        btnOpen = new Button();
        btnOpen.setText("Open");
        btnOpen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Open Clicked!");
            }
        });

        btnSave = new Button();
        btnSave.setText("Save");
        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Save Clicked!");
            }
        });

        getItems().addAll(btnNew, btnOpen, btnSave);
    }

    private void buildEditControls() {
        btnUndo = new Button();
        btnUndo.setText("Undo");
        btnUndo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Undo Clicked!");
            }
        });

        btnRedo = new Button();
        btnRedo.setText("Redo");
        btnRedo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Redo Clicked!");
            }
        });

        getItems().addAll(btnUndo, btnRedo);
    }

    private void buildColorControls() {
        btnColorPicker = new ColorPicker();
        btnColorPicker.getStyleClass().add("split-button");

        btnColorPicker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Color Chosen: " + btnColorPicker.getValue());
            }
        });

        getItems().addAll(btnColorPicker);
    }

}