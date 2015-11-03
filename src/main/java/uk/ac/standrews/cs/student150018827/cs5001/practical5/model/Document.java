package uk.ac.standrews.cs.student150018827.cs5001.practical5.model;

import javafx.scene.shape.Shape;

import java.nio.file.Path;
import java.util.*;

public class Document {

    private String name;
    private Path file;

    private int width;
    private int height;

    private List<List<Shape>> layers;

    public Document() {
        layers = new ArrayList<List<Shape>>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Path getFile() {
        return file;
    }

    public void setFile(Path file) {
        this.file = file;
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

    public void addLayer() {
        layers.add(new ArrayList<Shape>());
    }

    public void removeLayer(List<Shape> layer) {
        layers.remove(layer);
    }

    public void addShape(List<Shape> layer, Shape shape) {
        layers.get(layers.indexOf(layer)).add(shape);
    }

    public void removeShape(List<Shape> layer, Shape shape) {
        layers.get(layers.indexOf(layer)).remove(shape);
    }

    public List<List<Shape>> getLayers() {
        return layers;
    }

    public List<Shape> getLayer(int index) {
        return layers.get(index);
    }

}