package uk.ac.standrews.cs.student150018827.cs5001.practical5.model.objects;

public class Ellipse extends javafx.scene.shape.Ellipse implements CloneableNode {

    public Ellipse() {
        super();
    }

    @Override
    public Ellipse clone() {
        Ellipse clone = new Ellipse();

        clone.setCenterX(this.getCenterX());
        clone.setCenterY(this.getCenterY());
        clone.setRadiusX(this.getRadiusX());
        clone.setRadiusY(this.getRadiusY());
        clone.getTransforms().addAll(this.getTransforms());

        clone.setFill(this.getFill());

        clone.setCursor(this.getCursor());
        clone.setOnMouseMoved(this.getOnMouseMoved());
        clone.setOnMousePressed(this.getOnMousePressed());
        clone.setOnMouseDragged(this.getOnMouseDragged());
        clone.setOnMouseReleased(this.getOnMouseReleased());

        return clone;
    }

}