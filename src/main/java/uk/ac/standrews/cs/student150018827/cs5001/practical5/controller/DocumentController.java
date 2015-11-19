package uk.ac.standrews.cs.student150018827.cs5001.practical5.controller;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.main.Data;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.Document;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.GUIState;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.objects.Ellipse;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.objects.Line;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.objects.Rectangle;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.MainScene;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class DocumentController {

    private MainController mainController;

    private Document document;

    private GUIState guiState;

    private MainScene mainScene;

    public DocumentController(MainController mainController) {
        this.mainController = mainController;

        mainScene = mainController.getGUIController().getMainWindow().getMainScene();

        guiState = mainController.getGUIController().getGuiState();
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Document loadDocumentFromSvg(File file) throws IOException {
        return null;
    }

    public void saveDocumentToSvg(File file) throws IOException {
        SvgController svgController = mainController.getSvgController();

        svgController.createSVGDocument();
        svgController.setDimensions(document.getWidth(), document.getHeight());
        svgController.setTitle(document.getTitle());
        svgController.setObjects(document.getObjects());

        svgController.output(file);

        document.setSaved(true);
    }

    public Document createDocument() {
        document = new Document(mainController);

        HistoryController.getInstance(mainController).reset();
        HistoryController.getInstance(mainController).setDocument(document);

        return document;
    }

    public void setDimensions(int width, int height) {
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
        HistoryController.getInstance(mainController).createHistoryPoint();
    }

    public void clear() {
        if (document != null) {
            document.removeAllObjects();
            HistoryController.getInstance(mainController).createHistoryPoint();
            guiState.setSelectedObject(null);
        }
    }

    public boolean closeDocument() {
        boolean continueTask = true;

        if (document != null) {
            boolean close = false;

            if (document.isSaved()) {
                close = true;
            } else {
                Stage mainStage = mainController.getGUIController().getMainWindow().getMainStage();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.initOwner(mainStage);
                alert.setHeaderText("Unsaved Changes");
                alert.setContentText("Save changes to document \"" + document.getTitle() + "\" before closing?");

                ButtonType buttonTypeSave = new ButtonType("Save");
                ButtonType buttonTypeCloseWithoutSaving = new ButtonType("Close without saving");
                ButtonType buttonTypeCancel = new ButtonType("Cancel");

                alert.getButtonTypes().setAll(buttonTypeSave, buttonTypeCloseWithoutSaving, buttonTypeCancel);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == buttonTypeSave) {
                    mainController.getGUIController().saveFile(mainStage);
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

                // Reset History
                HistoryController.getInstance(mainController).reset();

                // Clear Artboard and clean up MainScene
                mainScene.clearArtBoard();
                mainScene.getStatusBar().clear();
                mainScene.hideBanner();
                mainScene.activateControls(false);
            }
        }

        return continueTask;
    }

    public void duplicateObject(Node object) {
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

    public void moveObjectToBottom(Node object) {
        document.moveObjectToBottom(object);
    }

    public void moveObjectDown(Node object) {
        document.moveObjectDown(object);
    }

    public void moveObjectUp(Node object) {
        document.moveObjectUp(object);
    }

    public void moveObjectToTop(Node object) {
        document.moveObjectToTop(object);
    }

    public void exportBitmap(File file, double scaleFactor) throws IOException {
        int width = (int) (scaleFactor * document.getWidth());
        int height = (int) (scaleFactor * document.getHeight());

        Group exportGroup = new Group();

        Rectangle backgroundRectangle = new Rectangle();
        backgroundRectangle.setX(0);
        backgroundRectangle.setY(0);
        backgroundRectangle.setWidth(width);
        backgroundRectangle.setHeight(height);
        backgroundRectangle.setFill(Color.TRANSPARENT);
        exportGroup.getChildren().add(backgroundRectangle);

        for (Node object : document.getObjects()) {
            if (object instanceof Rectangle) {
                Rectangle clone = ((Rectangle) object).clone();

                clone.setX(scaleFactor * clone.getX());
                clone.setY(scaleFactor * clone.getY());
                clone.setWidth(scaleFactor * clone.getWidth());
                clone.setHeight(scaleFactor * clone.getHeight());

                exportGroup.getChildren().add(clone);
            }

            if (object instanceof Ellipse) {
                Ellipse clone = ((Ellipse) object).clone();

                clone.setCenterX(scaleFactor * clone.getCenterX());
                clone.setCenterY(scaleFactor * clone.getCenterY());
                clone.setRadiusX(scaleFactor * clone.getRadiusX());
                clone.setRadiusY(scaleFactor * clone.getRadiusY());

                exportGroup.getChildren().add(clone);
            }

            if (object instanceof Line) {
                Line clone = ((Line) object).clone();

                clone.setStartX(scaleFactor * clone.getStartX());
                clone.setStartY(scaleFactor * clone.getStartY());
                clone.setEndX(scaleFactor * clone.getEndX());
                clone.setEndY(scaleFactor * clone.getEndY());

                exportGroup.getChildren().add(clone);
            }
        }

        SnapshotParameters snapshotParameters = new SnapshotParameters();
        snapshotParameters.setFill(Color.TRANSPARENT);

        WritableImage image = new WritableImage(width, height);
        exportGroup.snapshot(snapshotParameters, image);

        ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
    }

}