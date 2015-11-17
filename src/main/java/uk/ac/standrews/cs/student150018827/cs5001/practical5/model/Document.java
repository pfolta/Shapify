package uk.ac.standrews.cs.student150018827.cs5001.practical5.model;

import javafx.scene.Node;
import javafx.scene.transform.Rotate;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.MainController;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.focusoutline.FocusOutline;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.focusoutline.ResizeAnchor;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Document {

    private MainController mainController;

    private List<Observer> observers;

    private String title;
    private Path file;

    private boolean isSaved;

    private int width;
    private int height;

    private List<Node> objects;

    public Document(MainController mainController) {
        this.mainController = mainController;

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
        notifyObservers();
    }

    public void removeObject(Node object) {
        objects.remove(object);
        notifyObservers();
    }

    public void removeAllObjects() {
        objects.clear();
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

        notifyObservers();
    }

    public void moveObjectDown(Node object) {
        int index = objects.indexOf(object);

        if (index > 0) {
            Collections.swap(objects, index, index - 1);

            notifyObservers();
        }
    }

    public void moveObjectUp(Node object) {
        int index = objects.indexOf(object);

        if (index < objects.size() - 1) {
            Collections.swap(objects, index, index + 1);

            notifyObservers();
        }
    }

    public void moveObjectToTop(Node object) {
        int index;

        while ((index = objects.indexOf(object)) < objects.size() - 1) {
            Collections.swap(objects, index, index + 1);
        }

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
    }

}