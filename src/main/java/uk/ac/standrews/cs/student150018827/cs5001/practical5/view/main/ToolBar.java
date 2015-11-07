package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main;

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
    private Button moveToBottomButton;
    private Button moveDownButton;
    private Button moveUpButton;
    private Button moveToTopButton;

    private ColorPicker colorPicker;

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
        newButton.setOnAction(event -> {
            if (mainController.getDocumentController().closeDocument()) {
                mainController.getGUIController().openNewDrawingDialog((Stage) ToolBar.this.getScene().getWindow());
            }
        });

        openButton = new Button();
        openButton.setText("Open...");
        openButton.setTooltip(new Tooltip("Open (Ctrl+O)"));
        openButton.setOnAction(event -> {
            if (mainController.getDocumentController().closeDocument()) {
                mainController.getGUIController().getMainWindow().openFile();
            }
        });

        saveButton = new Button();
        saveButton.setText("Save");
        saveButton.setTooltip(new Tooltip("Save (Ctrl+S)"));
        saveButton.setOnAction(event -> mainController.getGUIController().getMainWindow().saveFile());

        getItems().addAll(newButton, openButton, saveButton);
    }

    private void buildEditControls() {
        undoButton = new Button();
        undoButton.setText("Undo");
        undoButton.setTooltip(new Tooltip("Undo (Ctrl+Z)"));
        undoButton.setOnAction(event -> System.out.println("Undo Clicked!"));

        redoButton = new Button();
        redoButton.setText("Redo");
        redoButton.setTooltip(new Tooltip("Redo (Ctrl+Shift+Z)"));
        redoButton.setOnAction(event -> System.out.println("Redo Clicked!"));

        moveToBottomButton = new Button();
        moveToBottomButton.setText("Move to Bottom");
        moveToBottomButton.setTooltip(new Tooltip("Move Selection to Bottom (End)"));
        moveToBottomButton.setDisable(true);
        moveToBottomButton.setOnAction(event -> {
            DocumentController documentController = mainController.getDocumentController();
            GUIState guiState = mainController.getGUIController().getGuiState();

            documentController.moveObjectToBottom(guiState.getSelectedObject());
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

        moveToTopButton = new Button();
        moveToTopButton.setText("Move to Top");
        moveToTopButton.setTooltip(new Tooltip("Move Selection to Top (Home)"));
        moveToTopButton.setDisable(true);
        moveToTopButton.setOnAction(event -> {
            DocumentController documentController = mainController.getDocumentController();
            GUIState guiState = mainController.getGUIController().getGuiState();

            documentController.moveObjectToTop(guiState.getSelectedObject());
        });

        getItems().addAll(
            undoButton,
            redoButton,
            moveToBottomButton,
            moveDownButton,
            moveUpButton,
            moveToTopButton
        );
    }

    private void buildColorControls() {
        colorPicker = new ColorPicker();
        colorPicker.getStyleClass().add("split-button");
        colorPicker.setValue(Color.BLACK);
        colorPicker.setOnAction(event -> mainController.getGUIController().getGuiState().setCurrentForeground(colorPicker.getValue()));

        getItems().addAll(colorPicker);
    }

    private void buildToolControls() {
        toolToggleGroup = new ToggleGroup();

        selectToolButton = new ToggleButton();
        selectToolButton.setText("Select");
        selectToolButton.setToggleGroup(toolToggleGroup);
        selectToolButton.setOnAction(event -> mainController.getGUIController().setSelectedTool(DrawTools.SELECT_TOOL));

        rectangleToolButton = new ToggleButton();
        rectangleToolButton.setText("Rectangle");
        rectangleToolButton.setToggleGroup(toolToggleGroup);
        rectangleToolButton.setOnAction(event -> mainController.getGUIController().setSelectedTool(DrawTools.RECTANGLE_TOOL));

        ellipseToolButton = new ToggleButton();
        ellipseToolButton.setText("Ellipse");
        ellipseToolButton.setToggleGroup(toolToggleGroup);
        ellipseToolButton.setOnAction(event -> mainController.getGUIController().setSelectedTool(DrawTools.ELLIPSE_TOOL));

        lineToolButton = new ToggleButton();
        lineToolButton.setText("Line");
        lineToolButton.setToggleGroup(toolToggleGroup);
        lineToolButton.setOnAction(event -> mainController.getGUIController().setSelectedTool(DrawTools.LINE_TOOL));

        getItems().addAll(
            selectToolButton,
            rectangleToolButton,
            ellipseToolButton,
            lineToolButton
        );
    }

    public void activateControls(boolean activate) {
        saveButton.setDisable(!activate);

        colorPicker.setDisable(!activate);

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
            colorPicker.setValue(guiState.getCurrentForeground());
        }

        if (guiState.getSelectedDrawTool() != null) {
            switch (guiState.getSelectedDrawTool()) {
                case SELECT_TOOL: {
                    selectToolButton.setSelected(true);
                    break;
                }
                case RECTANGLE_TOOL: {
                    rectangleToolButton.setSelected(true);
                    break;
                }
                case ELLIPSE_TOOL: {
                    ellipseToolButton.setSelected(true);
                    break;
                }
                case LINE_TOOL: {
                    lineToolButton.setSelected(true);
                    break;
                }
            }
        }
    }

    public void objectSelected(boolean selected) {
        moveToBottomButton.setDisable(!selected);
        moveDownButton.setDisable(!selected);
        moveUpButton.setDisable(!selected);
        moveToTopButton.setDisable(!selected);
    }

}