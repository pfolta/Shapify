package uk.ac.standrews.cs.student150018827.cs5001.practical5.controller;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.main.Data;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.Document;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.GUIState;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.objects.Ellipse;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.objects.Line;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.objects.Rectangle;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.MainScene;

import java.io.IOException;
import java.util.Optional;

public class DocumentController {

    private MainController mainController;

    private FileController fileController;
    private Document document;

    private MainScene mainScene;

    public DocumentController(MainController mainController) {
        this.mainController = mainController;

        fileController = new FileController();
        mainScene = mainController.getGUIController().getMainWindow().getMainScene();
    }

    public Document getDocument() {
        return document;
    }

    public Document loadDocumentFromFile(String path) throws IOException {
        document = fileController.loadDocumentFromFile(path);
        return document;
    }

    public void saveDocumentToFile() throws IOException {
        fileController.saveDocument(document);
    }

    public Document createDocument() {
        document = new Document();
        return document;
    }

    public void setDimension(int width, int height) {
        document.setDimensions(width, height);

        mainScene.setArtBoard(document.getWidth(), document.getHeight());
        mainScene.activateControls(true);
    }

    public void setTitle(String title) {
        document.setTitle(title);
    }

    public void addObject(Node object) {
        document.addObject(object);
    }

    public void removeObject(Node object) {
        document.removeObject(object);
    }

    public boolean closeDocument() {
        boolean continueTask = true;

        GUIState guiState = mainController.getGUIController().getGuiState();

        if (document != null) {
            boolean close = false;

            if (document.isSaved()) {
                close = true;
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.initOwner(mainController.getGUIController().getMainWindow().getMainStage());
                alert.setHeaderText("Unsaved Changes");
                alert.setContentText("Save changes to document \"" + document.getTitle() + "\" before closing?");

                ButtonType buttonTypeSave = new ButtonType("Save");
                ButtonType buttonTypeCloseWithoutSaving = new ButtonType("Close without saving");
                ButtonType buttonTypeCancel = new ButtonType("Cancel");

                alert.getButtonTypes().setAll(buttonTypeSave, buttonTypeCloseWithoutSaving, buttonTypeCancel);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == buttonTypeSave) {
                    mainController.getGUIController().getMainWindow().saveFile();
                    close = true;
                } else if (result.get() == buttonTypeCloseWithoutSaving) {
                    close = true;
                } else {
                    continueTask = false;
                }
            }

            if (close) {
                // Clear selected object
                guiState.setSelectedObject(null);

                // Clear document
                document = null;

                // Clear Artboard
                mainScene.clearArtBoard();
                mainScene.hideBanner();
                mainScene.activateControls(false);
            }
        }

        return continueTask;
    }

    public void duplicateObject(Node object) {
        GUIState guiState = mainController.getGUIController().getGuiState();

        if (object instanceof Rectangle) {
            Rectangle selectedRectangle = (Rectangle) object;

            Rectangle clone = selectedRectangle.clone();
            clone.setX(clone.getX() + Data.DUPLICATION_OFFSET_X);
            clone.setY(clone.getY() + Data.DUPLICATION_OFFSET_Y);

            mainController.getDocumentController().addObject(clone);
            guiState.setSelectedObject(clone);
        }

        if (object instanceof Ellipse) {
            Ellipse selectedEllipse = (Ellipse) object;

            Ellipse clone = selectedEllipse.clone();
            clone.setCenterX(clone.getCenterX() + Data.DUPLICATION_OFFSET_X);
            clone.setCenterY(clone.getCenterY() + Data.DUPLICATION_OFFSET_Y);

            mainController.getDocumentController().addObject(clone);
            guiState.setSelectedObject(clone);
        }

        if (object instanceof Line) {
            Line selectedLine = (Line) object;

            Line clone = selectedLine.clone();
            clone.setStartX(clone.getStartX() + Data.DUPLICATION_OFFSET_X);
            clone.setStartY(clone.getStartY() + Data.DUPLICATION_OFFSET_Y);
            clone.setEndX(clone.getEndX() + Data.DUPLICATION_OFFSET_X);
            clone.setEndY(clone.getEndY() + Data.DUPLICATION_OFFSET_Y);

            mainController.getDocumentController().addObject(clone);
            guiState.setSelectedObject(clone);
        }
    }

    public void moveObjectDown(Node object) {
        document.moveObjectDown(object);
    }

    public void moveObjectUp(Node object) {
        document.moveObjectUp(object);
    }

}