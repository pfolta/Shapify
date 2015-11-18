package uk.ac.standrews.cs.student150018827.cs5001.practical5.controller;

import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.Document;

import java.util.LinkedList;
import java.util.List;

public class HistoryController {

    private MainController mainController;
    private Document document;

    private List<Document> history;
    private int historyPointer;

    public HistoryController(MainController mainController) {
        this.mainController = mainController;

        history = new LinkedList<>();
        historyPointer = 0;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public void undo() {
        System.out.println("Undo Called!");

        if (isUndoAvailable()) {
            System.out.println("Undo Available!");
        }
    }

    public boolean isUndoAvailable() {
        return (historyPointer > 0);
    }

    public void redo() {
        System.out.println("Redo Called!");

        if (isRedoAvailable()) {
            System.out.println("Redo Available!");
        }
    }

    public boolean isRedoAvailable() {
        return (historyPointer < history.size());
    }

    public void reset() {
        history.clear();
        historyPointer = -1;
        createHistoryPoint();
    }

    public void createHistoryPoint() {
        Document clone = document.clone();
        history.add(clone);

        historyPointer++;
    }

}