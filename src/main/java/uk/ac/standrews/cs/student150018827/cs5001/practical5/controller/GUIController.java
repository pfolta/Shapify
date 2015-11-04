package uk.ac.standrews.cs.student150018827.cs5001.practical5.controller;

import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.stage.Stage;
import sun.util.resources.cldr.om.CurrencyNames_om;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.Document;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.about.AboutStage;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.DrawTools;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.ArtBoard;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.MainWindow;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.eventhandlers.*;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.newdrawing.NewDrawingStage;

import java.util.List;

public class GUIController {

    private MainController mainController;

    private MainWindow mainWindow;

    public GUIController(MainController mainController, Stage mainStage) {
        this.mainController = mainController;

        this.mainWindow = new MainWindow(mainController, mainStage);
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

    public void setSelectedTool(DrawTools selectedTool) {
        ArtBoard artBoard = getMainWindow().getMainScene().getArtBoard();
        Document document = mainController.getDocumentController().getDocument();

        switch (selectedTool) {
            case SELECT_TOOL: {
                getMainWindow().getMainScene().hideBanner();

                artBoard.setCursor(Cursor.DEFAULT);

                MouseEventHandler mouseEventHandler = new SelectEventHandler(mainController);

                artBoard.setMouseEventHandler(mouseEventHandler);

                List<Node> objects = document.getObjects();

                for(Node object : objects) {
                    object.setCursor(Cursor.MOVE);

                    object.setOnMouseMoved(mouseEventHandler.getMouseMovedEventHandler());
                    object.setOnMousePressed(mouseEventHandler.getMousePressedEventHandler());
                    object.setOnMouseDragged(mouseEventHandler.getMouseDraggedEventHandler());
                    object.setOnMouseReleased(mouseEventHandler.getMouseReleasedEventHandler());
                }

                break;
            }
            case RECTANGLE_TOOL: {
                getMainWindow().getMainScene().showBanner("Hold SHIFT down while drawing to create a perfect square.");

                artBoard.setCursor(Cursor.CROSSHAIR);
                artBoard.setMouseEventHandler(new RectangleEventHandler(mainController));

                break;
            }
            case ELLIPSE_TOOL: {
                getMainWindow().getMainScene().showBanner("Hold SHIFT down while drawing to create a perfect circle.");

                artBoard.setCursor(Cursor.CROSSHAIR);
                artBoard.setMouseEventHandler(new EllipseEventHandler(mainController));

                break;
            }
            case LINE_TOOL: {
                getMainWindow().getMainScene().showBanner("Hold SHIFT down while drawing to create a perfectly straight line.");

                artBoard.setCursor(Cursor.CROSSHAIR);
                artBoard.setMouseEventHandler(new LineEventHandler(mainController));
            }
        }
    }

}