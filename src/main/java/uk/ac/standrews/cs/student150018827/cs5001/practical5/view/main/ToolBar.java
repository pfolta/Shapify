package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.MainController;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.DrawTools;

public class ToolBar extends javafx.scene.control.ToolBar {

    private MainController mainController;

    private Button newButton;
    private Button openButton;
    private Button saveButton;

    private Button undoButton;
    private Button redoButton;

    private ColorPicker colorPickerButton;

    private ToggleGroup toolToggleGroup;
    private ToggleButton selectToolButton;
    private ToggleButton rectangleToolButton;
    private ToggleButton ellipseToolButton;
    private ToggleButton lineToolButton;

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
        newButton = new Button();
        newButton.setText("New");
        newButton.setTooltip(new Tooltip("New (Ctrl+N)"));
        newButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (mainController.getDocumentController().closeDocument()) {
                    mainController.getGUIController().openNewDrawingDialog((Stage) ToolBar.this.getScene().getWindow());
                }
            }
        });

        openButton = new Button();
        openButton.setText("Open...");
        openButton.setTooltip(new Tooltip("Open (Ctrl+O)"));
        openButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (mainController.getDocumentController().closeDocument()) {
                    mainController.getGUIController().getMainWindow().openFile();
                }
            }
        });

        saveButton = new Button();
        saveButton.setText("Save");
        saveButton.setTooltip(new Tooltip("Save (Ctrl+S)"));
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainController.getGUIController().getMainWindow().saveFile();
            }
        });

        getItems().addAll(newButton, openButton, saveButton);
    }

    private void buildEditControls() {
        undoButton = new Button();
        undoButton.setText("Undo");
        undoButton.setTooltip(new Tooltip("Undo (Ctrl+Z)"));
        undoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Undo Clicked!");
            }
        });

        redoButton = new Button();
        redoButton.setText("Redo");
        redoButton.setTooltip(new Tooltip("Redo (Ctrl+Shift+Z)"));
        redoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Redo Clicked!");
            }
        });

        getItems().addAll(undoButton, redoButton);
    }

    private void buildColorControls() {
        colorPickerButton = new ColorPicker();
        colorPickerButton.setValue(Color.BLACK);
        colorPickerButton.getStyleClass().add("split-button");

        colorPickerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainController.getDocumentController().setCurrentForeground(colorPickerButton.getValue());
            }
        });

        getItems().addAll(colorPickerButton);
    }

    private void buildToolControls() {
        toolToggleGroup = new ToggleGroup();
        toolToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> ov, Toggle toggle, Toggle newToggle) {
                if (newToggle == null) {
                    toggle.setSelected(true);
                }
            }
        });

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

        lineToolButton = new ToggleButton();
        lineToolButton.setText("Line");
        lineToolButton.setToggleGroup(toolToggleGroup);
        lineToolButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainController.getGUIController().setSelectedTool(DrawTools.LINE_TOOL);
            }
        });

        getItems().addAll(
            selectToolButton,
            rectangleToolButton,
            ellipseToolButton,
            lineToolButton
        );
    }

}