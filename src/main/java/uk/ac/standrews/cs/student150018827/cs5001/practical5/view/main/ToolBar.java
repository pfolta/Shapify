package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Separator;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.MainController;

public class ToolBar extends javafx.scene.control.ToolBar {

    private MainController mainController;

    private Button btnNew;
    private Button btnOpen;
    private Button btnSave;

    private Button btnUndo;
    private Button btnRedo;

    private ColorPicker btnColorPicker;

    public ToolBar(MainController mainController) {
        super();

        this.mainController = mainController;

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
        btnNew.setTooltip(new Tooltip("New (Ctrl+N)"));
        btnNew.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (mainController.getDocumentController().closeDocument()) {
                    mainController.getGUIController().openNewDrawingDialog((Stage) ToolBar.this.getScene().getWindow());
                }
            }
        });

        btnOpen = new Button();
        btnOpen.setText("Open...");
        btnOpen.setTooltip(new Tooltip("Open (Ctrl+O)"));
        btnOpen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainController.getGUIController().getMainWindow().openFile();
            }
        });

        btnSave = new Button();
        btnSave.setText("Save");
        btnSave.setTooltip(new Tooltip("Save (Ctrl+S)"));
        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainController.getGUIController().getMainWindow().saveFile();
            }
        });

        getItems().addAll(btnNew, btnOpen, btnSave);
    }

    private void buildEditControls() {
        btnUndo = new Button();
        btnUndo.setText("Undo");
        btnUndo.setTooltip(new Tooltip("Undo (Ctrl+Z)"));
        btnUndo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Undo Clicked!");
            }
        });

        btnRedo = new Button();
        btnRedo.setText("Redo");
        btnRedo.setTooltip(new Tooltip("Redo (Ctrl+Shift+Z)"));
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