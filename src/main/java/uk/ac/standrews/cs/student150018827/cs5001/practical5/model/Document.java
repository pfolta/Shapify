package uk.ac.standrews.cs.student150018827.cs5001.practical5.model;

import javafx.scene.Node;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Document {

    private String title;
    private Path file;

    private boolean isSaved;

    private int width;
    private int height;

    private List<Node> objects;

    public Document() {
        objects = new ArrayList<Node>();
        isSaved = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Path getFile() {
        return file;
    }

    public void setFile(Path file) {
        this.file = file;
    }

    public boolean isSaved() {
        return isSaved;
    }

    public void setSaved(boolean saved) {
        this.isSaved = saved;
    }

    public void setDimensions(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void addObject(Node object) {
        objects.add(object);
    }

    public void removeShape(Node object) {
        objects.remove(object);
    }

    public List<Node> getObjects() {
        return objects;
    }

}