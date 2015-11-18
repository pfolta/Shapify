package uk.ac.standrews.cs.student150018827.cs5001.practical5.model;

import javafx.scene.Node;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Shear;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.HistoryController;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.MainController;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.objects.CloneableNode;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.focusoutline.FocusOutline;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.focusoutline.ResizeAnchor;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Document {

    private MainController mainController;

    private HistoryController historyController;

    private List<Observer> observers;

    private String title;
    private Path file;

    private boolean isSaved;

    private int width;
    private int height;

    private List<Node> objects;

    public Document(MainController mainController) {
        this.mainController = mainController;

        historyController = mainController.getDocumentController().getHistoryController();

        observers = new ArrayList<>();

        objects = new ArrayList<>();
        isSaved = false;
    }

    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        observers.forEach(Observer::update);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyObservers();
    }

    public Path getFile() {
        return file;
    }

    public void setFile(Path file) {
        this.file = file;
        notifyObservers();
    }

    public boolean isSaved() {
        return isSaved;
    }

    public void setSaved(boolean saved) {
        this.isSaved = saved;
        notifyObservers();
    }

    public void setDimensions(int width, int height) {
        this.width = width;
        this.height = height;
        notifyObservers();
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
        notifyObservers();
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
        notifyObservers();
    }

    public void addObject(Node object) {
        objects.add(object);
//        historyController.createHistoryPoint();
        notifyObservers();
    }

    public void removeObject(Node object) {
        objects.remove(object);
//        historyController.createHistoryPoint();
        notifyObservers();
    }

    public void removeAllObjects() {
        objects.clear();
//        historyController.createHistoryPoint();
        notifyObservers();
    }

    public List<Node> getObjects() {
        return objects;
    }

    public void moveObjectToBottom(Node object) {
        int index;

        while ((index = objects.indexOf(object)) > 0) {
            Collections.swap(objects, index, index - 1);
        }

//        historyController.createHistoryPoint();
        notifyObservers();
    }

    public void moveObjectDown(Node object) {
        int index = objects.indexOf(object);

        if (index > 0) {
            Collections.swap(objects, index, index - 1);

//            historyController.createHistoryPoint();
            notifyObservers();
        }
    }

    public void moveObjectUp(Node object) {
        int index = objects.indexOf(object);

        if (index < objects.size() - 1) {
            Collections.swap(objects, index, index + 1);

//            historyController.createHistoryPoint();
            notifyObservers();
        }
    }

    public void moveObjectToTop(Node object) {
        int index;

        while ((index = objects.indexOf(object)) < objects.size() - 1) {
            Collections.swap(objects, index, index + 1);
        }

//        historyController.createHistoryPoint();
        notifyObservers();
    }

    public void rotateSelectedObject(double angle) {
        GUIState guiState = mainController.getGUIController().getGuiState();

        FocusOutline focusOutline = guiState.getFocusOutline();

        Rotate rotation = new Rotate();
        rotation.setAngle(angle);

        rotation.setPivotX(focusOutline.getRotateAnchor().getCenterX());
        rotation.setPivotY(focusOutline.getRotateAnchor().getCenterY());

        focusOutline.getFocusRectangle().getTransforms().add(rotation);
        focusOutline.getRotateAnchor().getTransforms().add(rotation);

        guiState.getSelectedObject().getTransforms().add(rotation);

        for (ResizeAnchor resizeAnchor : focusOutline.getResizeAnchors()) {
            resizeAnchor.getTransforms().add(rotation);
        }

//        historyController.createHistoryPoint();
    }

    public void shearSelectedObject(double x, double y) {
        GUIState guiState = mainController.getGUIController().getGuiState();

        FocusOutline focusOutline = guiState.getFocusOutline();

        Shear shearing = new Shear();
        shearing.setX(x);
        shearing.setY(y);

        shearing.setPivotX(focusOutline.getRotateAnchor().getCenterX());
        shearing.setPivotY(focusOutline.getRotateAnchor().getCenterY());

        focusOutline.getFocusRectangle().getTransforms().add(shearing);
        focusOutline.getRotateAnchor().getTransforms().add(shearing);

        guiState.getSelectedObject().getTransforms().add(shearing);

        for (ResizeAnchor resizeAnchor : focusOutline.getResizeAnchors()) {
            resizeAnchor.getTransforms().add(shearing);
        }

//        historyController.createHistoryPoint();
    }

    @Override
    public Document clone() {
        Document clone = new Document(this.mainController);

        clone.setWidth(this.getWidth());
        clone.setHeight(this.getHeight());
        clone.setTitle(this.getTitle());

        clone.setSaved(this.isSaved());

        for (Node object : this.getObjects()) {
            CloneableNode cloneableNode = (CloneableNode) object;
            Node clonedObject = cloneableNode.clone();

            clone.addObject(clonedObject);
        }

        return clone;
    }

}