package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main;

import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.DocumentController;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.MainController;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.Document;
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

    private MenuButton arrangeButton;
    private MenuItem moveToBottomMenuItem;
    private MenuItem moveDownMenuItem;
    private MenuItem moveUpMenuItem;
    private MenuItem moveToTopMenuItem;
    private MenuItem rotate90DegRightMenuItem;
    private MenuItem getRotate90DegLeftMenuItem;

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
                mainController.getGUIController().openFile(mainController.getGUIController().getMainWindow().getMainStage());
            }
        });

        saveButton = new Button();
        saveButton.setText("Save");
        saveButton.setTooltip(new Tooltip("Save (Ctrl+S)"));
        saveButton.setOnAction(event -> mainController.getGUIController().saveFile(mainController.getGUIController().getMainWindow().getMainStage()));

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

        arrangeButton = new MenuButton();
        arrangeButton.setText("Arrange");
        arrangeButton.setGraphic(new ImageView(new Image(ClassLoader.getSystemResourceAsStream("icons/32x32/shape_move_back.png"))));
        arrangeButton.setContentDisplay(ContentDisplay.TOP);
        arrangeButton.setDisable(true);

        moveToBottomMenuItem = new MenuItem();
        moveToBottomMenuItem.setText("Move to _Bottom");
        moveToBottomMenuItem.setGraphic(new ImageView(new Image(ClassLoader.getSystemResourceAsStream("icons/16x16/shape_move_back.png"))));
        moveToBottomMenuItem.setOnAction(event -> {
            DocumentController documentController = mainController.getDocumentController();
            GUIState guiState = mainController.getGUIController().getGuiState();

            documentController.moveObjectToBottom(guiState.getSelectedObject());
        });

        moveDownMenuItem = new MenuItem();
        moveDownMenuItem.setText("Move _Down");
        moveDownMenuItem.setGraphic(new ImageView(new Image(ClassLoader.getSystemResourceAsStream("icons/16x16/shape_move_backwards.png"))));
        moveDownMenuItem.setOnAction(event -> {
            DocumentController documentController = mainController.getDocumentController();
            GUIState guiState = mainController.getGUIController().getGuiState();

            documentController.moveObjectDown(guiState.getSelectedObject());
        });

        moveUpMenuItem = new MenuItem();
        moveUpMenuItem.setText("Move _Up");
        moveUpMenuItem.setGraphic(new ImageView(new Image(ClassLoader.getSystemResourceAsStream("icons/16x16/shape_move_forwards.png"))));
        moveUpMenuItem.setOnAction(event -> {
            DocumentController documentController = mainController.getDocumentController();
            GUIState guiState = mainController.getGUIController().getGuiState();

            documentController.moveObjectUp(guiState.getSelectedObject());
        });

        moveToTopMenuItem = new MenuItem();
        moveToTopMenuItem.setText("Move to _Top");
        moveToTopMenuItem.setGraphic(new ImageView(new Image(ClassLoader.getSystemResourceAsStream("icons/16x16/shape_move_front.png"))));
        moveToTopMenuItem.setOnAction(event -> {
            DocumentController documentController = mainController.getDocumentController();
            GUIState guiState = mainController.getGUIController().getGuiState();

            documentController.moveObjectToTop(guiState.getSelectedObject());
        });

        rotate90DegRightMenuItem = new MenuItem();
        rotate90DegRightMenuItem.setText("Rotate 90° _Right");
        rotate90DegRightMenuItem.setGraphic(new ImageView(new Image(ClassLoader.getSystemResourceAsStream("icons/16x16/shape_rotate_clockwise.png"))));
        rotate90DegRightMenuItem.setOnAction(event -> {
            Document document = mainController.getDocumentController().getDocument();
            GUIState guiState = mainController.getGUIController().getGuiState();

            document.rotateObject(guiState.getSelectedObject(), true, -90.0);
        });

        getRotate90DegLeftMenuItem = new MenuItem();
        getRotate90DegLeftMenuItem.setText("Rotate 90° _Left");
        getRotate90DegLeftMenuItem.setGraphic(new ImageView(new Image(ClassLoader.getSystemResourceAsStream("icons/16x16/shape_rotate_anticlockwise.png"))));
        getRotate90DegLeftMenuItem.setOnAction(event -> {
            Document document = mainController.getDocumentController().getDocument();
            GUIState guiState = mainController.getGUIController().getGuiState();

            document.rotateObject(guiState.getSelectedObject(), true, 90.0);
        });

        arrangeButton.getItems().addAll(
                moveToBottomMenuItem,
                moveDownMenuItem,
                moveUpMenuItem,
                moveToTopMenuItem,
                new SeparatorMenuItem(),
                rotate90DegRightMenuItem,
                getRotate90DegLeftMenuItem
        );

        getItems().addAll(
            undoButton,
            redoButton,
            arrangeButton
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

        colorPicker.setValue(guiState.getCurrentForeground());

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
        arrangeButton.setDisable(!selected);
    }

}