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
    private MenuItem getRotate90DegLeftMenuItem;

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
        undoButton.setOnAction(event -> mainController.getDocumentController().getHistoryController().undo());

        redoButton = new Button();
        redoButton.setText("Redo");
        redoButton.setTooltip(new Tooltip("Redo (Ctrl+Shift+Z)"));
        redoButton.setOnAction(event -> mainController.getDocumentController().getHistoryController().redo());

        arrangeMenuButton = new MenuButton();
        arrangeMenuButton.setText("Arrange");
        arrangeMenuButton.setGraphic(new ImageView(new Image(ClassLoader.getSystemResourceAsStream("icons/32x32/shape_move_back.png"))));
        //arrangeMenuButton.setContentDisplay(ContentDisplay.TOP);
        arrangeMenuButton.setDisable(true);

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

            document.rotateSelectedObject(-90.0);
        });

        getRotate90DegLeftMenuItem = new MenuItem();
        getRotate90DegLeftMenuItem.setText("Rotate 90° _Left");
        getRotate90DegLeftMenuItem.setGraphic(new ImageView(new Image(ClassLoader.getSystemResourceAsStream("icons/16x16/shape_rotate_anticlockwise.png"))));
        getRotate90DegLeftMenuItem.setOnAction(event -> {
            Document document = mainController.getDocumentController().getDocument();

            document.rotateSelectedObject(90.0);
        });

        arrangeMenuButton.getItems().addAll(
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
        strokeWidthMenuButton.setGraphic(new ImageView(new Image(ClassLoader.getSystemResourceAsStream("icons/32x32/border_weight.png"))));
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

        fillColorPicker.setDisable(!activate);
        strokeColorPicker.setDisable(!activate);
        strokeWidthMenuButton.setDisable(!activate);

        selectToolButton.setDisable(!activate);
        selectToolButton.setSelected(true);
        rectangleToolButton.setDisable(!activate);
        ellipseToolButton.setDisable(!activate);
        lineToolButton.setDisable(!activate);
    }

    @Override
    public void update() {
        GUIState guiState = mainController.getGUIController().getGuiState();

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