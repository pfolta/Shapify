package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main;

import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.DocumentController;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.HistoryController;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.MainController;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.GUIState;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.Observer;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.DrawTools;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.StrokeWidth;

public class ToolBar extends javafx.scene.control.ToolBar implements Observer {

    private MainController mainController;

    private Button newButton;
    private Button openButton;
    private Button saveButton;

    private Button undoButton;
    private Button redoButton;

    private MenuButton arrangeMenuButton;
    private MenuItem moveToBottomMenuItem;
    private MenuItem moveDownMenuItem;
    private MenuItem moveUpMenuItem;
    private MenuItem moveToTopMenuItem;
    private MenuItem rotate90DegRightMenuItem;
    private MenuItem rotate90DegLeftMenuItem;

    private ColorPicker fillColorPicker;
    private ColorPicker strokeColorPicker;

    private MenuButton strokeWidthMenuButton;
    private ToggleGroup strokeWidthToggleGroup;
    private RadioMenuItem strokeWidthNoneMenuItem;
    private RadioMenuItem strokeWidthThinMenuItem;
    private RadioMenuItem strokeWidthMediumMenuItem;
    private RadioMenuItem strokeWidthThickMenuItem;
    private RadioMenuItem strokeWidthExtraThickMenuItem;

    private ToggleGroup toolToggleGroup;
    private ToggleButton selectToolButton;
    private ToggleButton rectangleToolButton;
    private ToggleButton ellipseToolButton;
    private ToggleButton lineToolButton;
    private Button imageToolButton;

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
        openButton.setGraphic(new ImageView(new Image(ClassLoader.getSystemResourceAsStream("icons/16x16/folder_page.png"))));
        openButton.setTooltip(new Tooltip("Open (Ctrl+O)"));
        openButton.setOnAction(event -> {
            if (mainController.getDocumentController().closeDocument()) {
                mainController.getGUIController().openFile(mainController.getGUIController().getMainWindow().getMainStage());
            }
        });

        saveButton = new Button();
        saveButton.setGraphic(new ImageView(new Image(ClassLoader.getSystemResourceAsStream("icons/16x16/diskette.png"))));
        saveButton.setTooltip(new Tooltip("Save (Ctrl+S)"));
        saveButton.setOnAction(event -> mainController.getGUIController().saveFile(mainController.getGUIController().getMainWindow().getMainStage()));

        getItems().addAll(newButton, openButton, saveButton);
    }

    private void buildEditControls() {
        undoButton = new Button();
        undoButton.setGraphic(new ImageView(new Image(ClassLoader.getSystemResourceAsStream("icons/16x16/undo.png"))));
        undoButton.setTooltip(new Tooltip("Undo (Ctrl+Z)"));
        undoButton.setDisable(true);
        undoButton.setOnAction(event -> HistoryController.getInstance(mainController).undo());

        redoButton = new Button();
        redoButton.setGraphic(new ImageView(new Image(ClassLoader.getSystemResourceAsStream("icons/16x16/redo.png"))));
        redoButton.setDisable(true);
        redoButton.setTooltip(new Tooltip("Redo (Ctrl+Shift+Z)"));
        redoButton.setOnAction(event -> HistoryController.getInstance(mainController).redo());

        arrangeMenuButton = new MenuButton();
        arrangeMenuButton.setText("Arrange");
        arrangeMenuButton.setGraphic(new ImageView(new Image(ClassLoader.getSystemResourceAsStream("icons/16x16/shape_move_back.png"))));
        arrangeMenuButton.setDisable(true);

        moveToBottomMenuItem = new MenuItem();
        moveToBottomMenuItem.setText("Move to _Bottom");
        moveToBottomMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.END));
        moveToBottomMenuItem.setGraphic(new ImageView(new Image(ClassLoader.getSystemResourceAsStream("icons/16x16/shape_move_back.png"))));
        moveToBottomMenuItem.setOnAction(event -> {
            DocumentController documentController = mainController.getDocumentController();
            GUIState guiState = mainController.getGUIController().getGuiState();

            documentController.moveObjectToBottom(guiState.getSelectedObject());
        });

        moveDownMenuItem = new MenuItem();
        moveDownMenuItem.setText("Move _Down");
        moveDownMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.PAGE_DOWN));
        moveDownMenuItem.setGraphic(new ImageView(new Image(ClassLoader.getSystemResourceAsStream("icons/16x16/shape_move_backwards.png"))));
        moveDownMenuItem.setOnAction(event -> {
            DocumentController documentController = mainController.getDocumentController();
            GUIState guiState = mainController.getGUIController().getGuiState();

            documentController.moveObjectDown(guiState.getSelectedObject());
        });

        moveUpMenuItem = new MenuItem();
        moveUpMenuItem.setText("Move _Up");
        moveUpMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.PAGE_UP));
        moveUpMenuItem.setGraphic(new ImageView(new Image(ClassLoader.getSystemResourceAsStream("icons/16x16/shape_move_forwards.png"))));
        moveUpMenuItem.setOnAction(event -> {
            DocumentController documentController = mainController.getDocumentController();
            GUIState guiState = mainController.getGUIController().getGuiState();

            documentController.moveObjectUp(guiState.getSelectedObject());
        });

        moveToTopMenuItem = new MenuItem();
        moveToTopMenuItem.setText("Move to _Top");
        moveToTopMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.HOME));
        moveToTopMenuItem.setGraphic(new ImageView(new Image(ClassLoader.getSystemResourceAsStream("icons/16x16/shape_move_front.png"))));
        moveToTopMenuItem.setOnAction(event -> {
            DocumentController documentController = mainController.getDocumentController();
            GUIState guiState = mainController.getGUIController().getGuiState();

            documentController.moveObjectToTop(guiState.getSelectedObject());
        });

        rotate90DegRightMenuItem = new MenuItem();
        rotate90DegRightMenuItem.setText("Rotate 90° _Right");
        rotate90DegRightMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.RIGHT, KeyCombination.CONTROL_DOWN));
        rotate90DegRightMenuItem.setGraphic(new ImageView(new Image(ClassLoader.getSystemResourceAsStream("icons/16x16/shape_rotate_clockwise.png"))));
        rotate90DegRightMenuItem.setOnAction(event -> mainController.getDocumentController().getDocument().rotateSelectedObject(90.0));

        rotate90DegLeftMenuItem = new MenuItem();
        rotate90DegLeftMenuItem.setText("Rotate 90° _Left");
        rotate90DegLeftMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.LEFT, KeyCombination.CONTROL_DOWN));
        rotate90DegLeftMenuItem.setGraphic(new ImageView(new Image(ClassLoader.getSystemResourceAsStream("icons/16x16/shape_rotate_anticlockwise.png"))));
        rotate90DegLeftMenuItem.setOnAction(event -> mainController.getDocumentController().getDocument().rotateSelectedObject(-90.0));

        arrangeMenuButton.getItems().addAll(
            moveToBottomMenuItem,
            moveDownMenuItem,
            moveUpMenuItem,
            moveToTopMenuItem,
            new SeparatorMenuItem(),
            rotate90DegRightMenuItem,
            rotate90DegLeftMenuItem
        );

        getItems().addAll(
            undoButton,
            redoButton,
            arrangeMenuButton
        );
    }

    private void buildColorControls() {
        fillColorPicker = new ColorPicker();
        fillColorPicker.setTooltip(new Tooltip("Set Fill Color"));
        fillColorPicker.setValue(Color.BLACK);
        fillColorPicker.setMaxHeight(Double.MAX_VALUE);
        fillColorPicker.setOnAction(event -> mainController.getGUIController().getGuiState().setFillColor(fillColorPicker.getValue()));

        strokeColorPicker = new ColorPicker();
        strokeColorPicker.setTooltip(new Tooltip("Set Stroke Color"));
        strokeColorPicker.setValue(Color.BLACK);
        strokeColorPicker.setMaxHeight(Double.MAX_VALUE);
        strokeColorPicker.setOnAction(event -> mainController.getGUIController().getGuiState().setStrokeColor(strokeColorPicker.getValue()));

        strokeWidthMenuButton = new MenuButton();
        strokeWidthMenuButton.setText("Stroke Width");
        strokeWidthMenuButton.setGraphic(new ImageView(new Image(ClassLoader.getSystemResourceAsStream("icons/16x16/border_weight.png"))));
        strokeWidthMenuButton.setDisable(true);

        strokeWidthToggleGroup = new ToggleGroup();

        strokeWidthNoneMenuItem = new RadioMenuItem();
        strokeWidthNoneMenuItem.setText("None (0px)");
        strokeWidthNoneMenuItem.setToggleGroup(strokeWidthToggleGroup);
        strokeWidthNoneMenuItem.setOnAction(event -> mainController.getGUIController().getGuiState().setStrokeWidth(StrokeWidth.NONE));

        strokeWidthThinMenuItem = new RadioMenuItem();
        strokeWidthThinMenuItem.setText("Thin (1px)");
        strokeWidthThinMenuItem.setToggleGroup(strokeWidthToggleGroup);
        strokeWidthThinMenuItem.setOnAction(event -> mainController.getGUIController().getGuiState().setStrokeWidth(StrokeWidth.THIN));

        strokeWidthMediumMenuItem = new RadioMenuItem();
        strokeWidthMediumMenuItem.setText("Medium (3px)");
        strokeWidthMediumMenuItem.setToggleGroup(strokeWidthToggleGroup);
        strokeWidthMediumMenuItem.setSelected(true);
        strokeWidthMediumMenuItem.setOnAction(event -> mainController.getGUIController().getGuiState().setStrokeWidth(StrokeWidth.MEDIUM));

        strokeWidthThickMenuItem = new RadioMenuItem();
        strokeWidthThickMenuItem.setText("Thick (5px)");
        strokeWidthThickMenuItem.setToggleGroup(strokeWidthToggleGroup);
        strokeWidthThickMenuItem.setOnAction(event -> mainController.getGUIController().getGuiState().setStrokeWidth(StrokeWidth.THICK));

        strokeWidthExtraThickMenuItem = new RadioMenuItem();
        strokeWidthExtraThickMenuItem.setText("Extra Thick (8px)");
        strokeWidthExtraThickMenuItem.setToggleGroup(strokeWidthToggleGroup);
        strokeWidthExtraThickMenuItem.setOnAction(event -> mainController.getGUIController().getGuiState().setStrokeWidth(StrokeWidth.EXTRA_THICK));

        strokeWidthMenuButton.getItems().addAll(
            strokeWidthNoneMenuItem,
            strokeWidthThinMenuItem,
            strokeWidthMediumMenuItem,
            strokeWidthThickMenuItem,
            strokeWidthExtraThickMenuItem
        );

        getItems().addAll(
            fillColorPicker,
            strokeColorPicker,
            strokeWidthMenuButton
        );
    }

    private void buildToolControls() {
        toolToggleGroup = new ToggleGroup();

        selectToolButton = new ToggleButton();
        selectToolButton.setGraphic(new ImageView(new Image(ClassLoader.getSystemResourceAsStream("icons/16x16/cursor.png"))));
        selectToolButton.setTooltip(new Tooltip("Select and Transform Objects"));
        selectToolButton.setToggleGroup(toolToggleGroup);
        selectToolButton.setOnAction(event -> mainController.getGUIController().setSelectedTool(DrawTools.SELECT_TOOL));

        rectangleToolButton = new ToggleButton();
        rectangleToolButton.setGraphic(new ImageView(new Image(ClassLoader.getSystemResourceAsStream("icons/16x16/shape_square.png"))));
        rectangleToolButton.setTooltip(new Tooltip("Draw Rectangles and Squares"));
        rectangleToolButton.setToggleGroup(toolToggleGroup);
        rectangleToolButton.setOnAction(event -> mainController.getGUIController().setSelectedTool(DrawTools.RECTANGLE_TOOL));

        ellipseToolButton = new ToggleButton();
        ellipseToolButton.setGraphic(new ImageView(new Image(ClassLoader.getSystemResourceAsStream("icons/16x16/draw_ellipse.png"))));
        ellipseToolButton.setTooltip(new Tooltip("Draw Ellipses and Circles"));
        ellipseToolButton.setToggleGroup(toolToggleGroup);
        ellipseToolButton.setOnAction(event -> mainController.getGUIController().setSelectedTool(DrawTools.ELLIPSE_TOOL));

        lineToolButton = new ToggleButton();
        lineToolButton.setGraphic(new ImageView(new Image(ClassLoader.getSystemResourceAsStream("icons/16x16/draw_line.png"))));
        lineToolButton.setTooltip(new Tooltip("Draw Lines"));
        lineToolButton.setToggleGroup(toolToggleGroup);
        lineToolButton.setOnAction(event -> mainController.getGUIController().setSelectedTool(DrawTools.LINE_TOOL));

        imageToolButton = new Button();
        imageToolButton.setGraphic(new ImageView(new Image(ClassLoader.getSystemResourceAsStream("icons/16x16/image.png"))));
        imageToolButton.setTooltip(new Tooltip("Import Image"));
        imageToolButton.setOnAction(event -> mainController.getGUIController().importImage(mainController.getGUIController().getMainWindow().getMainStage()));

        getItems().addAll(
            selectToolButton,
            rectangleToolButton,
            ellipseToolButton,
            lineToolButton,
            imageToolButton
        );
    }

    public void activateControls(boolean activate) {
        saveButton.setDisable(!activate);

        fillColorPicker.setDisable(!activate);
        strokeColorPicker.setDisable(!activate);
        strokeWidthMenuButton.setDisable(!activate);

        selectToolButton.setDisable(!activate);
        selectToolButton.setSelected(true);
        rectangleToolButton.setDisable(!activate);
        ellipseToolButton.setDisable(!activate);
        lineToolButton.setDisable(!activate);
        imageToolButton.setDisable(!activate);
    }

    @Override
    public void update() {
        GUIState guiState = mainController.getGUIController().getGuiState();

        undoButton.setDisable(!HistoryController.getInstance(mainController).isUndoAvailable());
        redoButton.setDisable(!HistoryController.getInstance(mainController).isRedoAvailable());

        fillColorPicker.setValue(guiState.getFillColor());
        strokeColorPicker.setValue(guiState.getStrokeColor());

        switch (guiState.getStrokeWidth()) {
            case NONE: {
                strokeWidthNoneMenuItem.setSelected(true);
                break;
            }
            case THIN: {
                strokeWidthThinMenuItem.setSelected(true);
                break;
            }
            case MEDIUM: {
                strokeWidthMediumMenuItem.setSelected(true);
                break;
            }
            case THICK: {
                strokeWidthThickMenuItem.setSelected(true);
                break;
            }
            case EXTRA_THICK: {
                strokeWidthExtraThickMenuItem.setSelected(true);
                break;
            }
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
        arrangeMenuButton.setDisable(!selected);
    }

}