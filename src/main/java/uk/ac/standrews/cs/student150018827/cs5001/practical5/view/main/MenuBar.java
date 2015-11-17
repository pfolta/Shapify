package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.DocumentController;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.MainController;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.Document;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.GUIState;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.Observer;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.DrawTools;

public class MenuBar extends javafx.scene.control.MenuBar implements Observer {

    private MainController mainController;

    private MenuItem fileNewMenuItem;
    private MenuItem fileOpenMenuItem;
    private MenuItem fileSaveMenuItem;
    private MenuItem fileSaveAsMenuItem;
    private MenuItem fileExportBitmap;
    private MenuItem fileCloseMenuItem;
    private MenuItem fileExitMenuItem;

    private MenuItem editUndoMenuItem;
    private MenuItem editRedoMenuItem;
    private MenuItem editClearArtboard;
    
    private MenuItem objectDuplicateMenuItem;
    private MenuItem objectRemoveMenuItem;
    private MenuItem objectMoveToBottomMenuItem;
    private MenuItem objectMoveDownMenuItem;
    private MenuItem objectMoveUpMenuItem;
    private MenuItem objectMoveToTopMenuItem;
    private MenuItem objectRotate90DegRightMenuItem;
    private MenuItem objectRotate90DegLeftMenuItem;
    private MenuItem objectDeselectMenuItem;

    private ColorPicker toolColorPicker;
    private CustomMenuItem toolColorPickerMenuItem;

    private ToggleGroup toolToggleGroup;
    private RadioMenuItem toolSelectToolMenuItem;
    private RadioMenuItem toolRectangleToolMenuItem;
    private RadioMenuItem toolEllipseToolMenuItem;
    private RadioMenuItem toolLineToolMenuItem;

    private CheckMenuItem viewMenuBarMenuItem;
    private CheckMenuItem viewToolBarMenuItem;
    private CheckMenuItem viewStatusBarMenuItem;

    private MenuItem viewZoomInMenuItem;
    private MenuItem viewZoomOutMenuItem;
    private MenuItem viewResetZoomMenuItem;
    private Slider viewZoomSlider;
    private CustomMenuItem viewZoomSliderMenuItem;

    private CheckMenuItem viewFullscreenMenuItem;

    private MenuItem helpAboutMenuItem;

    public MenuBar(MainController mainController) {
        super();

        this.mainController = mainController;

        Menu fileMenu = buildFileMenu();
        Menu editMenu = buildEditMenu();
        Menu objectMenu = buildObjectMeu();
        Menu toolMenu = buildToolMenu();
        Menu viewMenu = buildViewMenu();
        Menu helpMenu = buildHelpMenu();

        this.getMenus().addAll(
            fileMenu,
            editMenu,
            objectMenu,
            toolMenu,
            viewMenu,
            helpMenu
        );

        activateControls(false);
    }

    private Menu buildFileMenu() {
        Menu menu = new Menu();
        menu.setText("_File");

        fileNewMenuItem = new MenuItem();
        fileNewMenuItem.setText("_New");
        fileNewMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN));
        fileNewMenuItem.setOnAction(event -> {
            if (mainController.getDocumentController().closeDocument()) {
                mainController.getGUIController().openNewDrawingDialog(mainController.getGUIController().getMainWindow().getMainStage());
            }
        });

        fileOpenMenuItem = new MenuItem();
        fileOpenMenuItem.setText("_Open...");
        fileOpenMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));
        fileOpenMenuItem.setOnAction(event -> {
            if (mainController.getDocumentController().closeDocument()) {
                mainController.getGUIController().openFile(mainController.getGUIController().getMainWindow().getMainStage());
            }
        });

        fileSaveMenuItem = new MenuItem();
        fileSaveMenuItem.setText("_Save");
        fileSaveMenuItem.setGraphic(new ImageView(new Image(ClassLoader.getSystemResourceAsStream("icons/16x16/diskette.png"))));
        fileSaveMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
        fileSaveMenuItem.setOnAction(event -> mainController.getGUIController().saveFile(mainController.getGUIController().getMainWindow().getMainStage()));

        fileSaveAsMenuItem = new MenuItem();
        fileSaveAsMenuItem.setText("Save _As...");
        fileSaveAsMenuItem.setGraphic(new ImageView(new Image(ClassLoader.getSystemResourceAsStream("icons/16x16/save_as.png"))));
        fileSaveAsMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN));
        fileSaveAsMenuItem.setOnAction(event -> mainController.getGUIController().saveAsFile(mainController.getGUIController().getMainWindow().getMainStage()));

        fileExportBitmap = new MenuItem();
        fileExportBitmap.setText("_Export Bitmap...");
        fileExportBitmap.setOnAction(event -> mainController.getGUIController().openExportBitmapDialog(mainController.getGUIController().getMainWindow().getMainStage()));

        fileCloseMenuItem = new MenuItem();
        fileCloseMenuItem.setText("_Close");
        fileCloseMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.W, KeyCombination.CONTROL_DOWN));
        fileCloseMenuItem.setOnAction(event -> mainController.getDocumentController().closeDocument());

        fileExitMenuItem = new MenuItem();
        fileExitMenuItem.setText("E_xit");
        fileExitMenuItem.setGraphic(new ImageView(new Image(ClassLoader.getSystemResourceAsStream("icons/16x16/door_in.png"))));
        fileExitMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.F4, KeyCombination.ALT_DOWN));
        fileExitMenuItem.setOnAction(event -> mainController.exit());

        menu.getItems().addAll(
            fileNewMenuItem,
            new SeparatorMenuItem(),
            fileOpenMenuItem,
            fileSaveMenuItem,
            fileSaveAsMenuItem,
            new SeparatorMenuItem(),
            fileExportBitmap,
            new SeparatorMenuItem(),
            fileCloseMenuItem,
            fileExitMenuItem
        );

        return menu;
    }

    private Menu buildEditMenu() {
        Menu menu = new Menu();
        menu.setText("_Edit");

        editUndoMenuItem = new MenuItem();
        editUndoMenuItem.setText("_Undo");
        editUndoMenuItem.setGraphic(new ImageView(new Image(ClassLoader.getSystemResourceAsStream("icons/16x16/undo.png"))));
        editUndoMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN));
        editUndoMenuItem.setOnAction(event -> System.out.println("Undo Clicked!"));

        editRedoMenuItem = new MenuItem();
        editRedoMenuItem.setText("_Redo");
        editRedoMenuItem.setGraphic(new ImageView(new Image(ClassLoader.getSystemResourceAsStream("icons/16x16/redo.png"))));
        editRedoMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN));
        editRedoMenuItem.setOnAction(event -> System.out.println("Redo Clicked!"));

        editClearArtboard = new MenuItem();
        editClearArtboard.setText("_Clear Artboard");
        editClearArtboard.setOnAction(event -> mainController.getDocumentController().clear());

        menu.getItems().addAll(
            editUndoMenuItem,
            editRedoMenuItem,
            new SeparatorMenuItem(),
            editClearArtboard
        );

        return menu;
    }

    private Menu buildObjectMeu() {
        Menu menu = new Menu();
        menu.setText("_Object");

        objectDuplicateMenuItem = new MenuItem();
        objectDuplicateMenuItem.setText("D_uplicate");
        objectDuplicateMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.D, KeyCombination.CONTROL_DOWN));
        objectDuplicateMenuItem.setDisable(true);
        objectDuplicateMenuItem.setOnAction(event -> {
            GUIState guiState = mainController.getGUIController().getGuiState();
            Node selectedObject = guiState.getSelectedObject();

            DocumentController documentController = mainController.getDocumentController();
            documentController.duplicateObject(selectedObject);
        });

        objectRemoveMenuItem = new MenuItem();
        objectRemoveMenuItem.setText("_Remove");
        objectRemoveMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.DELETE));
        objectRemoveMenuItem.setDisable(true);
        objectRemoveMenuItem.setOnAction(event -> {
            GUIState guiState = mainController.getGUIController().getGuiState();

            mainController.getDocumentController().removeObject(guiState.getSelectedObject());
            guiState.setSelectedObject(null);
        });

        objectMoveToBottomMenuItem = new MenuItem();
        objectMoveToBottomMenuItem.setText("Move to _Bottom");
        objectMoveToBottomMenuItem.setGraphic(new ImageView(new Image(ClassLoader.getSystemResourceAsStream("icons/16x16/shape_move_back.png"))));
        objectMoveToBottomMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.END));
        objectMoveToBottomMenuItem.setDisable(true);
        objectMoveToBottomMenuItem.setOnAction(event -> {
            DocumentController documentController = mainController.getDocumentController();
            GUIState guiState = mainController.getGUIController().getGuiState();

            documentController.moveObjectToBottom(guiState.getSelectedObject());
        });

        objectMoveDownMenuItem = new MenuItem();
        objectMoveDownMenuItem.setText("Move _Down");
        objectMoveDownMenuItem.setGraphic(new ImageView(new Image(ClassLoader.getSystemResourceAsStream("icons/16x16/shape_move_backwards.png"))));
        objectMoveDownMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.PAGE_DOWN));
        objectMoveDownMenuItem.setDisable(true);
        objectMoveDownMenuItem.setOnAction(event -> {
            DocumentController documentController = mainController.getDocumentController();
            GUIState guiState = mainController.getGUIController().getGuiState();

            documentController.moveObjectDown(guiState.getSelectedObject());
        });

        objectMoveUpMenuItem = new MenuItem();
        objectMoveUpMenuItem.setText("Move _Up");
        objectMoveUpMenuItem.setGraphic(new ImageView(new Image(ClassLoader.getSystemResourceAsStream("icons/16x16/shape_move_forwards.png"))));
        objectMoveUpMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.PAGE_UP));
        objectMoveUpMenuItem.setDisable(true);
        objectMoveUpMenuItem.setOnAction(event -> {
            DocumentController documentController = mainController.getDocumentController();
            GUIState guiState = mainController.getGUIController().getGuiState();

            documentController.moveObjectUp(guiState.getSelectedObject());
        });

        objectMoveToTopMenuItem = new MenuItem();
        objectMoveToTopMenuItem.setText("Move to _Top");
        objectMoveToTopMenuItem.setGraphic(new ImageView(new Image(ClassLoader.getSystemResourceAsStream("icons/16x16/shape_move_front.png"))));
        objectMoveToTopMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.HOME));
        objectMoveToTopMenuItem.setDisable(true);
        objectMoveToTopMenuItem.setOnAction(event -> {
            DocumentController documentController = mainController.getDocumentController();
            GUIState guiState = mainController.getGUIController().getGuiState();

            documentController.moveObjectToTop(guiState.getSelectedObject());
        });

        objectRotate90DegRightMenuItem = new MenuItem();
        objectRotate90DegRightMenuItem.setText("Rotate 90° _Right");
        objectRotate90DegRightMenuItem.setGraphic(new ImageView(new Image(ClassLoader.getSystemResourceAsStream("icons/16x16/shape_rotate_clockwise.png"))));
        objectRotate90DegRightMenuItem.setDisable(true);
        objectRotate90DegRightMenuItem.setOnAction(event -> {
            Document document = mainController.getDocumentController().getDocument();
            document.rotateSelectedObject(-90.0);
        });

        objectRotate90DegLeftMenuItem = new MenuItem();
        objectRotate90DegLeftMenuItem.setText("Rotate 90° _Left");
        objectRotate90DegLeftMenuItem.setGraphic(new ImageView(new Image(ClassLoader.getSystemResourceAsStream("icons/16x16/shape_rotate_anticlockwise.png"))));
        objectRotate90DegLeftMenuItem.setDisable(true);
        objectRotate90DegLeftMenuItem.setOnAction(event -> {
            Document document = mainController.getDocumentController().getDocument();
            document.rotateSelectedObject(90.0);
        });

        objectDeselectMenuItem = new MenuItem();
        objectDeselectMenuItem.setText("D_eselect");
        objectDeselectMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.ESCAPE));
        objectDeselectMenuItem.setDisable(true);
        objectDeselectMenuItem.setOnAction(event -> {
            GUIState guiState = mainController.getGUIController().getGuiState();

            guiState.setSelectedObject(null);
        });

        menu.getItems().addAll(
            objectDuplicateMenuItem,
            objectRemoveMenuItem,
            new SeparatorMenuItem(),
            objectMoveToBottomMenuItem,
            objectMoveDownMenuItem,
            objectMoveUpMenuItem,
            objectMoveToTopMenuItem,
            new SeparatorMenuItem(),
            objectRotate90DegRightMenuItem,
            objectRotate90DegLeftMenuItem,
            new SeparatorMenuItem(),
            objectDeselectMenuItem
        );

        return menu;
    }

    private Menu buildToolMenu() {
        Menu menu = new Menu();
        menu.setText("_Tool");

        toolColorPicker = new ColorPicker();
        toolColorPicker.setDisable(true);
        toolColorPicker.getStyleClass().add("split-button");
        toolColorPicker.setValue(Color.BLACK);
        toolColorPicker.setOnAction(event -> mainController.getGUIController().getGuiState().setCurrentForeground(toolColorPicker.getValue()));

        toolColorPickerMenuItem = new CustomMenuItem(toolColorPicker);
        toolColorPickerMenuItem.setHideOnClick(false);
        toolColorPickerMenuItem.setDisable(true);

        toolToggleGroup = new ToggleGroup();

        toolSelectToolMenuItem = new RadioMenuItem();
        toolSelectToolMenuItem.setText("Select");
        toolSelectToolMenuItem.setToggleGroup(toolToggleGroup);
        toolSelectToolMenuItem.setOnAction(event -> mainController.getGUIController().setSelectedTool(DrawTools.SELECT_TOOL));

        toolRectangleToolMenuItem = new RadioMenuItem();
        toolRectangleToolMenuItem.setText("Rectangle");
        toolRectangleToolMenuItem.setGraphic(new ImageView(new Image(ClassLoader.getSystemResourceAsStream("icons/16x16/shape_square.png"))));
        toolRectangleToolMenuItem.setToggleGroup(toolToggleGroup);
        toolRectangleToolMenuItem.setOnAction(event -> mainController.getGUIController().setSelectedTool(DrawTools.RECTANGLE_TOOL));

        toolEllipseToolMenuItem = new RadioMenuItem();
        toolEllipseToolMenuItem.setText("Ellipse");
        toolEllipseToolMenuItem.setGraphic(new ImageView(new Image(ClassLoader.getSystemResourceAsStream("icons/16x16/draw_ellipse.png"))));
        toolEllipseToolMenuItem.setToggleGroup(toolToggleGroup);
        toolEllipseToolMenuItem.setOnAction(event -> mainController.getGUIController().setSelectedTool(DrawTools.ELLIPSE_TOOL));

        toolLineToolMenuItem = new RadioMenuItem();
        toolLineToolMenuItem.setText("Line");
        toolLineToolMenuItem.setGraphic(new ImageView(new Image(ClassLoader.getSystemResourceAsStream("icons/16x16/draw_line.png"))));
        toolLineToolMenuItem.setToggleGroup(toolToggleGroup);
        toolLineToolMenuItem.setOnAction(event -> mainController.getGUIController().setSelectedTool(DrawTools.LINE_TOOL));

        menu.getItems().addAll(
            toolColorPickerMenuItem,
            new SeparatorMenuItem(),
            toolSelectToolMenuItem,
            toolRectangleToolMenuItem,
            toolEllipseToolMenuItem,
            toolLineToolMenuItem
        );

        return menu;
    }

    private Menu buildViewMenu() {
        Menu menu = new Menu();
        menu.setText("_View");

        viewMenuBarMenuItem = new CheckMenuItem();
        viewMenuBarMenuItem.setText("_Menu Bar");
        viewMenuBarMenuItem.setSelected(true);
        viewMenuBarMenuItem.setOnAction(event -> mainController.getGUIController().getMainWindow().getMainScene().showMenuBar(viewMenuBarMenuItem.isSelected()));

        viewToolBarMenuItem = new CheckMenuItem();
        viewToolBarMenuItem.setText("_Toolbar");
        viewToolBarMenuItem.setSelected(true);
        viewToolBarMenuItem.setOnAction(event -> mainController.getGUIController().getMainWindow().getMainScene().showToolBar(viewToolBarMenuItem.isSelected()));

        viewStatusBarMenuItem = new CheckMenuItem();
        viewStatusBarMenuItem.setText("_Status Bar");
        viewStatusBarMenuItem.setSelected(true);
        viewStatusBarMenuItem.setOnAction(event -> mainController.getGUIController().getMainWindow().getMainScene().showStatusBar(viewStatusBarMenuItem.isSelected()));

        viewZoomInMenuItem = new MenuItem();
        viewZoomInMenuItem.setDisable(true);
        viewZoomInMenuItem.setText("_Zoom In");
        viewZoomInMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.PLUS, KeyCombination.CONTROL_DOWN));
        viewZoomInMenuItem.setOnAction(event -> mainController.getGUIController().getGuiState().setZoomLevel(mainController.getGUIController().getGuiState().getZoomLevel() + 0.25));

        viewZoomOutMenuItem = new MenuItem();
        viewZoomOutMenuItem.setDisable(true);
        viewZoomOutMenuItem.setText("_Zoom Out");
        viewZoomOutMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.MINUS, KeyCombination.CONTROL_DOWN));
        viewZoomOutMenuItem.setOnAction(event -> mainController.getGUIController().getGuiState().setZoomLevel(mainController.getGUIController().getGuiState().getZoomLevel() - 0.25));

        viewResetZoomMenuItem = new MenuItem();
        viewResetZoomMenuItem.setDisable(true);
        viewResetZoomMenuItem.setText("_Reset Zoom");
        viewResetZoomMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.DIGIT0, KeyCombination.CONTROL_DOWN));
        viewResetZoomMenuItem.setOnAction(event -> mainController.getGUIController().getGuiState().setZoomLevel(1.0));

        viewZoomSlider = new Slider();
        viewZoomSlider.setDisable(true);
        viewZoomSlider.setMin(0.1);
        viewZoomSlider.setMax(5.0);
        viewZoomSlider.setValue(1.0);
        viewZoomSlider.valueProperty().addListener(observable -> {
            mainController.getGUIController().getGuiState().setZoomLevel(viewZoomSlider.getValue());
        });

        viewZoomSliderMenuItem = new CustomMenuItem(viewZoomSlider);
        viewZoomSliderMenuItem.setHideOnClick(false);
        viewZoomSliderMenuItem.setDisable(true);

        viewFullscreenMenuItem = new CheckMenuItem();
        viewFullscreenMenuItem.setText("_Fullscreen");
        viewFullscreenMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.F11));
        viewFullscreenMenuItem.setOnAction(event -> mainController.getGUIController().getMainWindow().setFullscreen(viewFullscreenMenuItem.isSelected()));

        menu.getItems().addAll(
            viewMenuBarMenuItem,
            viewToolBarMenuItem,
            viewStatusBarMenuItem,
            new SeparatorMenuItem(),
            viewZoomInMenuItem,
            viewZoomOutMenuItem,
            viewResetZoomMenuItem,
            viewZoomSliderMenuItem,
            new SeparatorMenuItem(),
            viewFullscreenMenuItem
        );

        return menu;
    }

    private Menu buildHelpMenu() {
        Menu menu = new Menu();
        menu.setText("_Help");

        helpAboutMenuItem = new MenuItem();
        helpAboutMenuItem.setText("_About");
        helpAboutMenuItem.setOnAction(event -> mainController.getGUIController().openAboutDialog((Stage) MenuBar.this.getScene().getWindow()));

        menu.getItems().addAll(helpAboutMenuItem);

        return menu;
    }

    public void selectViewMenuBarItem(boolean selected) {
        viewMenuBarMenuItem.setSelected(selected);
    }

    public void selectViewToolBarItem(boolean selected) {
        viewToolBarMenuItem.setSelected(selected);
    }

    public void selectViewStatusBarItem(boolean selected) {
        viewStatusBarMenuItem.setSelected(selected);
    }

    public void activateControls(boolean activate) {
        fileSaveMenuItem.setDisable(!activate);
        fileSaveAsMenuItem.setDisable(!activate);
        fileExportBitmap.setDisable(!activate);
        fileCloseMenuItem.setDisable(!activate);

        editClearArtboard.setDisable(!activate);

        toolColorPicker.setDisable(!activate);
        toolColorPickerMenuItem.setDisable(!activate);
        toolSelectToolMenuItem.setDisable(!activate);
        toolSelectToolMenuItem.setSelected(true);
        toolRectangleToolMenuItem.setDisable(!activate);
        toolEllipseToolMenuItem.setDisable(!activate);
        toolLineToolMenuItem.setDisable(!activate);

        viewZoomInMenuItem.setDisable(!activate);
        viewZoomOutMenuItem.setDisable(!activate);
        viewResetZoomMenuItem.setDisable(!activate);
        viewZoomSlider.setDisable(!activate);
        viewZoomSliderMenuItem.setDisable(!activate);
    }

    @Override
    public void update() {
        GUIState guiState = mainController.getGUIController().getGuiState();

        toolColorPicker.setValue(guiState.getCurrentForeground());

        viewZoomSlider.setValue(guiState.getZoomLevel());

        if (guiState.getSelectedDrawTool() != null) {
            switch (guiState.getSelectedDrawTool()) {
                case SELECT_TOOL: {
                    toolSelectToolMenuItem.setSelected(true);
                    break;
                }
                case RECTANGLE_TOOL: {
                    toolRectangleToolMenuItem.setSelected(true);
                    break;
                }
                case ELLIPSE_TOOL: {
                    toolEllipseToolMenuItem.setSelected(true);
                    break;
                }
                case LINE_TOOL: {
                    toolLineToolMenuItem.setSelected(true);
                    break;
                }
            }
        }
    }

    public void objectSelected(boolean selected) {
        objectDuplicateMenuItem.setDisable(!selected);
        objectRemoveMenuItem.setDisable(!selected);

        objectMoveToBottomMenuItem.setDisable(!selected);
        objectMoveDownMenuItem.setDisable(!selected);
        objectMoveUpMenuItem.setDisable(!selected);
        objectMoveToTopMenuItem.setDisable(!selected);

        objectRotate90DegRightMenuItem.setDisable(!selected);
        objectRotate90DegLeftMenuItem.setDisable(!selected);

        objectDeselectMenuItem.setDisable(!selected);
    }

}