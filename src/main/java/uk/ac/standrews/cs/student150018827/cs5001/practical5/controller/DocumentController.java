package uk.ac.standrews.cs.student150018827.cs5001.practical5.controller;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.Document;

import java.io.IOException;
import java.util.Optional;

public class DocumentController {

    private MainController mainController;

    private FileController fileController;
    private Document document;

    private Color currentForeground;

    public DocumentController(MainController mainController) {
        this.mainController = mainController;
        fileController = new FileController();
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

        if (document != null) {
            boolean close = false;

            if (document.isSaved()) {
                close = true;
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
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
                document = null;
                mainController.getGUIController().getMainWindow().getMainScene().clearArtBoard();
            }
        }

        return continueTask;
    }

    public Color getCurrentForeground() {
        if (currentForeground == null) {
            return Color.BLACK;
        }

        return currentForeground;
    }

    public void setCurrentForeground(Color foreground) {
        currentForeground = foreground;
    }

}