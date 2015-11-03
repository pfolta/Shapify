package uk.ac.standrews.cs.student150018827.cs5001.practical5.controller;

import javafx.scene.shape.Shape;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DocumentController {

    private FileController fileController;
    private Document document;

    public DocumentController() {
        fileController = new FileController();
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

    public void addShape(Shape shape, List<Shape> layer) {
        document.addShape(layer, shape);
    }

    public void removeShape(Shape shape, List<Shape> layer) {
        document.removeShape(layer, shape);
    }

}