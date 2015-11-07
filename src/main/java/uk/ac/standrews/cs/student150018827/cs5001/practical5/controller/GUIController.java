package uk.ac.standrews.cs.student150018827.cs5001.practical5.controller;

import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.stage.Stage;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.Document;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.GUIState;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.DrawTools;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.about.AboutStage;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.ArtBoard;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.MainWindow;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.eventhandlers.*;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.newdrawing.NewDrawingStage;

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
                getMainWindow().getMainScene().hideBanner();
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

}