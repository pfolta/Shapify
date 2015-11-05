package uk.ac.standrews.cs.student150018827.cs5001.practical5.model;

import javafx.scene.Node;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Document {

    private List<Observer> observers;

    private String title;
    private Path file;

    private boolean isSaved;

    private int width;
    private int height;

    private List<Node> objects;

    public Document() {
        observers = new ArrayList<>();

        objects = new ArrayList<>();
        isSaved = false;
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
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

    public List<Node> getObjects() {
        return objects;
    }

}