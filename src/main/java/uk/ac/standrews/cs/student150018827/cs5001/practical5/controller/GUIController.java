package uk.ac.standrews.cs.student150018827.cs5001.practical5.controller;

import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.jdom2.JDOMException;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.Document;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.GUIState;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.objects.Image;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.DrawTools;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.about.AboutStage;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.exportbitmap.ExportBitmapStage;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.ArtBoard;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.MainWindow;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.eventhandlers.*;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.newdrawing.NewDrawingStage;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class GUIController {

    private MainController mainController;

    private GUIState guiState;
    private MainWindow mainWindow;

    public GUIController(MainController mainController, Stage mainStage) {
        this.mainController = mainController;

        guiState = new GUIState(mainController);
        mainWindow = new MainWindow(mainController, mainStage);
    }

    public void openMainWindow() {
        mainWindow.open();
    }

    public MainWindow getMainWindow() {
        return mainWindow;
    }

    public void closeMainWindow() {
        mainWindow.close();
    }

    public void openNewDrawingDialog(Stage parent) {
        NewDrawingStage newDrawingStage = new NewDrawingStage(mainController, parent);
        newDrawingStage.show();
        newDrawingStage.requestFocus();
    }

    public void openExportBitmapDialog(Stage parent) {
        ExportBitmapStage exportBitmapStage = new ExportBitmapStage(mainController, parent);
        exportBitmapStage.show();
        exportBitmapStage.requestFocus();
    }

    public void openAboutDialog(Stage parent) {
        AboutStage aboutStage = new AboutStage(mainController, parent);
        aboutStage.show();
        aboutStage.requestFocus();
    }

    public void setSelectedTool(DrawTools selectedDrawTool) {
        ArtBoard artBoard = getMainWindow().getMainScene().getArtBoard();
        Document document = mainController.getDocumentController().getDocument();

        List<Node> objects = document.getObjects();

        MouseEventHandler mouseEventHandler = null;

        switch (selectedDrawTool) {
            case SELECT_TOOL: {
                getMainWindow().getMainScene().showBanner("Hold SHIFT down while dragging an object to shear it.");
                mouseEventHandler = new SelectEventHandler(mainController);

                artBoard.setCursor(Cursor.DEFAULT);

                for(Node object : objects) {
                    object.setCursor(Cursor.MOVE);
                }

                break;
            }
            case RECTANGLE_TOOL: {
                getMainWindow().getMainScene().showBanner("Hold SHIFT down while drawing to create a perfect square.");
                mouseEventHandler = new RectangleEventHandler(mainController);

                artBoard.setCursor(Cursor.CROSSHAIR);

                for(Node object : objects) {
                    object.setCursor(Cursor.CROSSHAIR);
                }

                break;
            }
            case ELLIPSE_TOOL: {
                getMainWindow().getMainScene().showBanner("Hold SHIFT down while drawing to create a perfect circle.");
                mouseEventHandler = new EllipseEventHandler(mainController);

                artBoard.setCursor(Cursor.CROSSHAIR);

                for(Node object : objects) {
                    object.setCursor(Cursor.CROSSHAIR);
                }

                break;
            }
            case LINE_TOOL: {
                getMainWindow().getMainScene().showBanner("Hold SHIFT down while drawing to create a perfectly straight line.");
                mouseEventHandler = new LineEventHandler(mainController);

                artBoard.setCursor(Cursor.CROSSHAIR);

                for(Node object : objects) {
                    object.setCursor(Cursor.CROSSHAIR);
                }

                break;
            }
        }

        artBoard.setMouseEventHandler(mouseEventHandler);

        for(Node object : objects) {
            object.setOnMouseMoved(mouseEventHandler.getMouseMovedEventHandler());
            object.setOnMousePressed(mouseEventHandler.getMousePressedEventHandler());
            object.setOnMouseDragged(mouseEventHandler.getMouseDraggedEventHandler());
            object.setOnMouseReleased(mouseEventHandler.getMouseReleasedEventHandler());

            object.setOnContextMenuRequested(new ContextEventHandler(mainController));
        }

        guiState.setSelectedDrawTool(selectedDrawTool);
    }

    public GUIState getGuiState() {
        return guiState;
    }

    public boolean importImage(Stage parent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image to Import");
        fileChooser.setInitialDirectory(guiState.getLastUsedDirectory());

        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("All Images (*.jpg, *.jpeg, *.gif, *.png)", "*.jpg", "*.JPG", "*.jpeg", "*.JPEG", "*.gif", "*.GIF", "*.png", "*.PNG"),
            new FileChooser.ExtensionFilter("All Files (*.*)", "*.*"),
            new FileChooser.ExtensionFilter("JPEG Image (*.jpg, *.jpeg)", "*.jpg", "*.JPG", "*.jpeg", "*.JPEG"),
            new FileChooser.ExtensionFilter("GIF Image (*.gif)", "*.gif", "*.GIF"),
            new FileChooser.ExtensionFilter("PNG Image (*.png)", "*.png", "*.PNG")
        );

        File file = fileChooser.showOpenDialog(parent);

        if (file != null) {
            if (file.getParentFile().isDirectory()) {
                guiState.setLastUsedDirectory(file.getParentFile());
            }

            try {
                DocumentController documentController = mainController.getDocumentController();
                Document document = documentController.getDocument();

                Image image = documentController.importImage(file);

                guiState.setSelectedObject(image);
                setSelectedTool(DrawTools.SELECT_TOOL);
                document.notifyObservers();

                return true;
            } catch (IOException exception) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(parent);
                alert.setContentText("An error occured while trying to load the image: " + exception.getMessage());

                alert.showAndWait();
            }
        }

        return false;
    }

    public boolean openFile(Stage parent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select File to Open");
        fileChooser.setInitialDirectory(guiState.getLastUsedDirectory());

        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Scalable Vector Graphic (*.svg)", "*.svg", "*.SVG"),
            new FileChooser.ExtensionFilter("All Files (*.*)", "*.*")
        );

        File file = fileChooser.showOpenDialog(parent);

        if (file != null) {
            if (file.getParentFile().isDirectory()) {
                guiState.setLastUsedDirectory(file.getParentFile());
            }

            try {
                Document document = mainController.getDocumentController().loadDocumentFromSvg(file);

                document.registerObserver(mainController.getGUIController().getMainWindow().getMainScene());
                document.registerObserver(mainController.getGUIController().getMainWindow().getMainScene().getMenuBar());
                document.registerObserver(mainController.getGUIController().getMainWindow().getMainScene().getToolBar());
                document.registerObserver(mainController.getGUIController().getMainWindow().getMainScene().getStatusBar());
                guiState.registerObserver(mainController.getGUIController().getMainWindow().getMainScene());
                guiState.registerObserver(mainController.getGUIController().getMainWindow().getMainScene().getMenuBar());
                guiState.registerObserver(mainController.getGUIController().getMainWindow().getMainScene().getToolBar());
                guiState.registerObserver(mainController.getGUIController().getMainWindow().getMainScene().getStatusBar());

                mainController.getDocumentController().getDocument().setSaved(true);
                guiState.setFile(file);

                document.notifyObservers();

                setSelectedTool(DrawTools.SELECT_TOOL);

                // Create History Point
                HistoryController.getInstance(mainController).createHistoryPoint();

                return true;
            } catch (JDOMException | IOException exception) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(parent);
                alert.setContentText("An error occured while trying to load the document: " + exception.getMessage());

                alert.showAndWait();
            }
        }

        return false;
    }

    public boolean saveFile(Stage parent) {
        if (guiState.getFile() != null) {
            try {
                mainController.getDocumentController().saveDocumentToSvg(guiState.getFile());

                return true;
            } catch (IOException exception) {
                mainController.getDocumentController().getDocument().setSaved(false);

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(parent);
                alert.setContentText("An error occured while trying to save the document: " + exception.getMessage());

                alert.showAndWait();
            }

            return false;
        }

        return saveAsFile(parent);
    }

    public boolean saveAsFile(Stage parent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select File to Save To");
        fileChooser.setInitialDirectory(guiState.getLastUsedDirectory());
        fileChooser.setInitialFileName(mainController.getDocumentController().getDocument().getTitle() + ".svg");

        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Scalable Vector Graphic (*.svg)", "*.svg", "*.SVG"),
            new FileChooser.ExtensionFilter("All Files (*.*)", "*.*")
        );

        File file = fileChooser.showSaveDialog(parent);

        if (file != null) {
            if (file.getParentFile().isDirectory()) {
                guiState.setLastUsedDirectory(file.getParentFile());
            }

            try {
                mainController.getDocumentController().saveDocumentToSvg(file);

                guiState.setFile(file);

                return true;
            } catch (IOException exception) {
                mainController.getDocumentController().getDocument().setSaved(false);

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(parent);
                alert.setContentText("An error occured while trying to save the document: " + exception.getMessage());

                alert.showAndWait();
            }
        }

        return false;
    }

    public boolean exportBitmap(Stage parent, double scaleFactor) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select File to Export To");
        fileChooser.setInitialDirectory(guiState.getLastUsedDirectory());
        fileChooser.setInitialFileName(mainController.getDocumentController().getDocument().getTitle() + ".png");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("PNG Image (*.png)", "*.png", "*.PNG")
        );

        File file = fileChooser.showSaveDialog(parent);

        if (file != null) {
            if (file.getParentFile().isDirectory()) {
                guiState.setLastUsedDirectory(file.getParentFile());
            }

            try {
                mainController.getDocumentController().exportBitmap(file, scaleFactor);

                return true;
            } catch (IOException exception) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(parent);
                alert.setContentText("An error occured while trying to save the exported image: " + exception.getMessage());

                alert.showAndWait();
            }
        }

        return false;
    }

}