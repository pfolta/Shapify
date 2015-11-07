package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.DocumentController;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.MainController;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.GUIState;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.Observer;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.DrawTools;

public class MenuBar extends javafx.scene.control.MenuBar implements Observer {

    private MainController mainController;

    private MenuItem fileNewMenuItem;
    private MenuItem fileOpenMenuItem;
    private MenuItem fileSaveMenuItem;
    private MenuItem fileSaveAsMenuItem;
    private MenuItem fileExportMenuItem;
    private MenuItem fileCloseMenuItem;
    private MenuItem fileExitMenuItem;

    private MenuItem editUndoMenuItem;
    private MenuItem editRedoMenuItem;
    private MenuItem editDuplicateMenuItem;
    private MenuItem editRemoveMenuItem;
    private MenuItem editMoveToBottomMenuItem;
    private MenuItem editMoveDownMenuItem;
    private MenuItem editMoveUpMenuItem;
    private MenuItem editMoveToTopMenuItem;
    private MenuItem editDeselectMenuItem;

    private ColorPicker colorPicker;
    private CustomMenuItem toolColorPickerMenuItem;

    private ToggleGroup toolToggleGroup;
    private RadioMenuItem toolSelectToolMenuItem;
    private RadioMenuItem toolRectangleToolMenuItem;
    private RadioMenuItem toolEllipseToolMenuItem;
    private RadioMenuItem toolLineToolMenuItem;

    private CheckMenuItem viewMenuBarMenuItem;
    private CheckMenuItem viewToolBarMenuItem;
    private CheckMenuItem viewStatusBarMenuItem;
    private CheckMenuItem viewFullscreenMenuItem;

    private MenuItem helpAboutMenuItem;

    public MenuBar(MainController mainController) {
        super();

        this.mainController = mainController;

        Menu fileMenu = buildFileMenu();
        Menu editMenu = buildEditMenu();
        Menu toolMenu = buildToolMenu();
        Menu viewMenu = buildViewMenu();
        Menu helpMenu = buildHelpMenu();

        this.getMenus().addAll(
            fileMenu,
            editMenu,
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
                mainController.getGUIController().openNewDrawingDialog((Stage) MenuBar.this.getScene().getWindow());
            }
        });

        fileOpenMenuItem = new MenuItem();
        fileOpenMenuItem.setText("_Open...");
        fileOpenMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));
        fileOpenMenuItem.setOnAction(event -> {
            if (mainController.getDocumentController().closeDocument()) {
                mainController.getGUIController().getMainWindow().openFile();
            }
        });

        fileSaveMenuItem = new MenuItem();
        fileSaveMenuItem.setText("_Save");
        fileSaveMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
        fileSaveMenuItem.setOnAction(event -> mainController.getGUIController().getMainWindow().saveFile());

        fileSaveAsMenuItem = new MenuItem();
        fileSaveAsMenuItem.setText("Save _As...");
        fileSaveAsMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN));
        fileSaveAsMenuItem.setOnAction(event -> mainController.getGUIController().getMainWindow().saveAsFile());

        fileExportMenuItem = new MenuItem();
        fileExportMenuItem.setText("_Export...");
        fileExportMenuItem.setOnAction(event -> System.out.println("Export Clicked!"));

        fileCloseMenuItem = new MenuItem();
        fileCloseMenuItem.setText("_Close");
        fileCloseMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.W, KeyCombination.CONTROL_DOWN));
        fileCloseMenuItem.setOnAction(event -> mainController.getDocumentController().closeDocument());

        fileExitMenuItem = new MenuItem();
        fileExitMenuItem.setText("E_xit");
        fileExitMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.F4, KeyCombination.ALT_DOWN));
        fileExitMenuItem.setOnAction(event -> mainController.exit());

        menu.getItems().addAll(
            fileNewMenuItem,
            new SeparatorMenuItem(),
            fileOpenMenuItem,
            fileSaveMenuItem,
            fileSaveAsMenuItem,
            new SeparatorMenuItem(),
            fileExportMenuItem,
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
        editUndoMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN));
        editUndoMenuItem.setOnAction(event -> System.out.println("Undo Clicked!"));

        editRedoMenuItem = new MenuItem();
        editRedoMenuItem.setText("_Redo");
        editRedoMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN));
        editRedoMenuItem.setOnAction(event -> System.out.println("Redo Clicked!"));

        editDuplicateMenuItem = new MenuItem();
        editDuplicateMenuItem.setText("_Duplicate");
        editDuplicateMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.D, KeyCombination.CONTROL_DOWN));
        editDuplicateMenuItem.setDisable(true);
        editDuplicateMenuItem.setOnAction(event -> {
            GUIState guiState = mainController.getGUIController().getGuiState();
            Node selectedObject = guiState.getSelectedObject();

            DocumentController documentController = mainController.getDocumentController();
            documentController.duplicateObject(selectedObject);
        });

        editRemoveMenuItem = new MenuItem();
        editRemoveMenuItem.setText("_Remove");
        editRemoveMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.DELETE));
        editRemoveMenuItem.setDisable(true);
        editRemoveMenuItem.setOnAction(event -> {
            GUIState guiState = mainController.getGUIController().getGuiState();

            mainController.getDocumentController().removeObject(guiState.getSelectedObject());
            guiState.setSelectedObject(null);
        });

        editMoveToBottomMenuItem = new MenuItem();
        editMoveToBottomMenuItem.setText("Move to Bottom");
        editMoveToBottomMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.END));
        editMoveToBottomMenuItem.setDisable(true);
        editMoveToBottomMenuItem.setOnAction(event -> {
            DocumentController documentController = mainController.getDocumentController();
            GUIState guiState = mainController.getGUIController().getGuiState();

            documentController.moveObjectToBottom(guiState.getSelectedObject());
        });

        editMoveDownMenuItem = new MenuItem();
        editMoveDownMenuItem.setText("Move Down");
        editMoveDownMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.PAGE_DOWN));
        editMoveDownMenuItem.setDisable(true);
        editMoveDownMenuItem.setOnAction(event -> {
            DocumentController documentController = mainController.getDocumentController();
            GUIState guiState = mainController.getGUIController().getGuiState();

            documentController.moveObjectDown(guiState.getSelectedObject());
        });

        editMoveUpMenuItem = new MenuItem();
        editMoveUpMenuItem.setText("Move Up");
        editMoveUpMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.PAGE_UP));
        editMoveUpMenuItem.setDisable(true);
        editMoveUpMenuItem.setOnAction(event -> {
            DocumentController documentController = mainController.getDocumentController();
            GUIState guiState = mainController.getGUIController().getGuiState();

            documentController.moveObjectUp(guiState.getSelectedObject());
        });

        editMoveToTopMenuItem = new MenuItem();
        editMoveToTopMenuItem.setText("Move to Top");
        editMoveToTopMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.HOME));
        editMoveToTopMenuItem.setDisable(true);
        editMoveToTopMenuItem.setOnAction(event -> {
            DocumentController documentController = mainController.getDocumentController();
            GUIState guiState = mainController.getGUIController().getGuiState();

            documentController.moveObjectToTop(guiState.getSelectedObject());
        });

        editDeselectMenuItem = new MenuItem();
        editDeselectMenuItem.setText("_Deselect");
        editDeselectMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.ESCAPE));
        editDeselectMenuItem.setDisable(true);
        editDeselectMenuItem.setOnAction(event -> {
            GUIState guiState = mainController.getGUIController().getGuiState();

            guiState.setSelectedObject(null);
        });

        menu.getItems().addAll(
            editUndoMenuItem,
            editRedoMenuItem,
            new SeparatorMenuItem(),
            editDuplicateMenuItem,
            editRemoveMenuItem,
            new SeparatorMenuItem(),
            editMoveToBottomMenuItem,
            editMoveDownMenuItem,
            editMoveUpMenuItem,
            editMoveToTopMenuItem,
            new SeparatorMenuItem(),
            editDeselectMenuItem
        );

        return menu;
    }

    private Menu buildToolMenu() {
        Menu menu = new Menu();
        menu.setText("_Tool");

        colorPicker = new ColorPicker();
        colorPicker.getStyleClass().add("split-button");
        colorPicker.setValue(Color.BLACK);
        colorPicker.setOnAction(event -> mainController.getGUIController().getGuiState().setCurrentForeground(colorPicker.getValue()));

        toolColorPickerMenuItem = new CustomMenuItem(colorPicker);
        toolColorPickerMenuItem.setHideOnClick(false);

        toolToggleGroup = new ToggleGroup();

        toolSelectToolMenuItem = new RadioMenuItem();
        toolSelectToolMenuItem.setText("Select");
        toolSelectToolMenuItem.setToggleGroup(toolToggleGroup);
        toolSelectToolMenuItem.setOnAction(event -> mainController.getGUIController().setSelectedTool(DrawTools.SELECT_TOOL));

        toolRectangleToolMenuItem = new RadioMenuItem();
        toolRectangleToolMenuItem.setText("Rectangle");
        toolRectangleToolMenuItem.setToggleGroup(toolToggleGroup);
        toolRectangleToolMenuItem.setOnAction(event -> mainController.getGUIController().setSelectedTool(DrawTools.RECTANGLE_TOOL));

        toolEllipseToolMenuItem = new RadioMenuItem();
        toolEllipseToolMenuItem.setText("Ellipse");
        toolEllipseToolMenuItem.setToggleGroup(toolToggleGroup);
        toolEllipseToolMenuItem.setOnAction(event -> mainController.getGUIController().setSelectedTool(DrawTools.ELLIPSE_TOOL));

        toolLineToolMenuItem = new RadioMenuItem();
        toolLineToolMenuItem.setText("Line");
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

        viewFullscreenMenuItem = new CheckMenuItem();
        viewFullscreenMenuItem.setText("_Fullscreen");
        viewFullscreenMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.F11));
        viewFullscreenMenuItem.setOnAction(event -> mainController.getGUIController().getMainWindow().setFullscreen(viewFullscreenMenuItem.isSelected()));

        menu.getItems().addAll(
            viewMenuBarMenuItem,
            viewToolBarMenuItem,
            viewStatusBarMenuItem,
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
        fileExportMenuItem.setDisable(!activate);
        fileCloseMenuItem.setDisable(!activate);

        toolSelectToolMenuItem.setDisable(!activate);
        toolSelectToolMenuItem.setSelected(true);
        toolRectangleToolMenuItem.setDisable(!activate);
        toolEllipseToolMenuItem.setDisable(!activate);
        toolLineToolMenuItem.setDisable(!activate);
    }

    @Override
    public void update() {
        GUIState guiState = mainController.getGUIController().getGuiState();

        if (guiState != null) {
            if (guiState.getSelectedObject() != null) {
                colorPicker.setValue(guiState.getCurrentForeground());
            }

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
        editDuplicateMenuItem.setDisable(!selected);
        editRemoveMenuItem.setDisable(!selected);
        editMoveToBottomMenuItem.setDisable(!selected);
        editMoveDownMenuItem.setDisable(!selected);
        editMoveUpMenuItem.setDisable(!selected);
        editMoveToTopMenuItem.setDisable(!selected);
        editDeselectMenuItem.setDisable(!selected);
    }

}