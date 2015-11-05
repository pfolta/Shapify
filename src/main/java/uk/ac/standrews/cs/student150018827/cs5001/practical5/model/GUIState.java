package uk.ac.standrews.cs.student150018827.cs5001.practical5.model;

import javafx.scene.Node;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class GUIState {

    private List<Observer> observers;

    private Node selectedObject;
    private Color currentForeground;

    public GUIState() {
        observers = new ArrayList<Observer>();
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        observers.forEach((observer) -> observer.update());
    }

    public Node getSelectedObject() {
        return selectedObject;
    }

    public void setSelectedObject(Node selectedObject) {
        this.selectedObject = selectedObject;

        notifyObservers();
    }

    public Color getCurrentForeground() {
        if (currentForeground == null) {
            return Color.BLACK;
        }

        return currentForeground;
    }

    public void setCurrentForeground(Color foreground) {
        currentForeground = foreground;

        notifyObservers();
    }

}