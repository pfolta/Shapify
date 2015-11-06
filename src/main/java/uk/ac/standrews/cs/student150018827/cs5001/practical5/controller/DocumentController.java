package uk.ac.standrews.cs.student150018827.cs5001.practical5.controller;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.Document;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.GUIState;
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
                // Clear document
                document = null;

                // Clear Artboard
                mainScene.clearArtBoard();
                mainScene.hideBanner();
                mainScene.activateControls(false);

                // Clear selected object
                guiState.setSelectedObject(null);
            }
        }

        return continueTask;
    }

}