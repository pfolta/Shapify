package uk.ac.standrews.cs.student150018827.cs5001.practical5.controller;

import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.Document;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.GUIState;

import java.util.LinkedList;
import java.util.List;

public class HistoryController {

    private static HistoryController instance;

    private MainController mainController;
    private Document document;

    private List<Document> history;
    private volatile int historyPointer;

    private HistoryController(MainController mainController) {
        this.mainController = mainController;

        history = new LinkedList<>();
        historyPointer = -1;
    }

    public static HistoryController getInstance(MainController mainController) {
        if (instance == null) {
            instance = new HistoryController(mainController);
        }

        return instance;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public void undo() {
        if (isUndoAvailable()) {
            GUIState guiState = mainController.getGUIController().getGuiState();

            historyPointer--;

            mainController.getDocumentController().setDocument(history.get(historyPointer));
            guiState.setSelectedObject(null);

            document.notifyObservers();
        }
    }

    public boolean isUndoAvailable() {
        return (historyPointer > 0);
    }

    public void redo() {
        if (isRedoAvailable()) {
            historyPointer++;

            mainController.getDocumentController().setDocument(history.get(historyPointer));

            document.notifyObservers();
        }
    }

    public boolean isRedoAvailable() {
        return (historyPointer < history.size() - 1);
    }

    public void reset() {
        history.clear();
        historyPointer = -1;
    }

    public void createHistoryPoint() {
        if (historyPointer != (history.size() - 1)) {
            System.out.println("Need to restructure undo");

            for (int i = historyPointer + 1; i <= history.size(); i++) {
               history.remove(i);
            }
        }

        Document clone = document.clone();
        history.add(clone);
        historyPointer++;

        mainController.getDocumentController().getDocument().notifyObservers();
    }

}