package uk.ac.standrews.cs.student150018827.cs5001.practical5.model.objects;

public class Line extends javafx.scene.shape.Line {

    public Line() {
        super();
    }

    @Override
    public Line clone() {
        Line clone = new Line();

        clone.setStartX(this.getStartX());
        clone.setStartY(this.getStartY());
        clone.setEndX(this.getEndX());
        clone.setEndY(this.getEndY());
        clone.getTransforms().addAll(this.getTransforms());

        clone.setStroke(this.getStroke());
        clone.setStrokeWidth(this.getStrokeWidth());

        clone.setCursor(this.getCursor());
        clone.setOnMouseMoved(this.getOnMouseMoved());
        clone.setOnMousePressed(this.getOnMousePressed());
        clone.setOnMouseDragged(this.getOnMouseDragged());
        clone.setOnMouseReleased(this.getOnMouseReleased());

        return clone;
    }

}