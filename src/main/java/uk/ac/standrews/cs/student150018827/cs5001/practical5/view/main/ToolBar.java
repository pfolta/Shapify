package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.DocumentController;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.MainController;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.GUIState;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.Observer;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.DrawTools;

public class ToolBar extends javafx.scene.control.ToolBar implements Observer {

    private MainController mainController;

    private Button newButton;
    private Button openButton;
    private Button saveButton;

    private Button undoButton;
    private Button redoButton;
    private Button moveBottomButton;
    private Button moveDownButton;
    private Button moveUpButton;
    private Button moveTopButton;

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
        activateControls(false);
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

        moveBottomButton = new Button();
        moveBottomButton.setText("Move to Bottom");
        moveBottomButton.setTooltip(new Tooltip("Move Selection to Bottom (End)"));
        moveBottomButton.setDisable(true);
        moveBottomButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            }
        });

        moveDownButton = new Button();
        moveDownButton.setText("Move Down");
        moveDownButton.setTooltip(new Tooltip("Move Selection Down (Page Down)"));
        moveDownButton.setDisable(true);
        moveDownButton.setOnAction(event -> {
            DocumentController documentController = mainController.getDocumentController();
            GUIState guiState = mainController.getGUIController().getGuiState();

            documentController.moveObjectDown(guiState.getSelectedObject());
        });

        moveUpButton = new Button();
        moveUpButton.setText("Move Up");
        moveUpButton.setTooltip(new Tooltip("Move Selection Up (Page Up)"));
        moveUpButton.setDisable(true);
        moveUpButton.setOnAction(event -> {
            DocumentController documentController = mainController.getDocumentController();
            GUIState guiState = mainController.getGUIController().getGuiState();

            documentController.moveObjectUp(guiState.getSelectedObject());
        });

        moveTopButton = new Button();
        moveTopButton.setText("Move to Top");
        moveTopButton.setTooltip(new Tooltip("Move Selection to Top (Home)"));
        moveTopButton.setDisable(true);
        moveTopButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            }
        });

        getItems().addAll(
            undoButton,
            redoButton,
            moveBottomButton,
            moveDownButton,
            moveUpButton,
            moveTopButton
        );
    }

    private void buildColorControls() {
        colorPickerButton = new ColorPicker();
        colorPickerButton.getStyleClass().add("split-button");
        colorPickerButton.setValue(Color.BLACK);
        colorPickerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainController.getGUIController().getGuiState().setCurrentForeground(colorPickerButton.getValue());
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

    public void activateControls(boolean activate) {
        saveButton.setDisable(!activate);

        colorPickerButton.setDisable(!activate);

        selectToolButton.setDisable(!activate);
        selectToolButton.setSelected(true);
        rectangleToolButton.setDisable(!activate);
        ellipseToolButton.setDisable(!activate);
        lineToolButton.setDisable(!activate);
    }

    @Override
    public void update() {
        GUIState guiState = mainController.getGUIController().getGuiState();

        if (guiState.getSelectedObject() != null) {
            colorPickerButton.setValue(guiState.getCurrentForeground());
        }
    }

    public void objectSelected(boolean selected) {
        moveBottomButton.setDisable(!selected);
        moveDownButton.setDisable(!selected);
        moveUpButton.setDisable(!selected);
        moveTopButton.setDisable(!selected);
    }

}