package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.MainController;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.DrawTools;

public class ToolBar extends javafx.scene.control.ToolBar {

    private MainController mainController;

    private Button btnNew;
    private Button btnOpen;
    private Button btnSave;

    private Button btnUndo;
    private Button btnRedo;

    private ColorPicker btnColorPicker;

    private ToggleGroup toolToggleGroup;
    private ToggleButton selectToolButton;
    private ToggleButton rectangleToolButton;
    private ToggleButton ellipseToolButton;

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
        getItems().add(new Separator());
        buildToolControls();
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
                if (mainController.getDocumentController().closeDocument()) {
                    mainController.getGUIController().getMainWindow().openFile();
                }
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
        btnColorPicker.setValue(Color.BLACK);
        btnColorPicker.getStyleClass().add("split-button");

        btnColorPicker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainController.getDocumentController().setCurrentForeground(btnColorPicker.getValue());
            }
        });

        getItems().addAll(btnColorPicker);
    }

    private void buildToolControls() {
        toolToggleGroup = new ToggleGroup();

        selectToolButton = new ToggleButton();
        selectToolButton.setText("Select");
        selectToolButton.setToggleGroup(toolToggleGroup);
        selectToolButton.setSelected(true);
        selectToolButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainController.getGUIController().setSelectedTool(DrawTools.SELECT_TOOL);
            }
        });

        rectangleToolButton = new ToggleButton();
        rectangleToolButton.setText("Rectangle");
        rectangleToolButton.setToggleGroup(toolToggleGroup);
        rectangleToolButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainController.getGUIController().setSelectedTool(DrawTools.RECTANGLE_TOOL);
            }
        });

        ellipseToolButton = new ToggleButton();
        ellipseToolButton.setText("Ellipse");
        ellipseToolButton.setToggleGroup(toolToggleGroup);
        ellipseToolButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainController.getGUIController().setSelectedTool(DrawTools.ELLIPSE_TOOL);
            }
        });

        getItems().addAll(
            selectToolButton,
            rectangleToolButton,
            ellipseToolButton
        );
    }

}