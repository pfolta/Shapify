package uk.ac.standrews.cs.student150018827.cs5001.practical5.model.objects;

public class Rectangle extends javafx.scene.shape.Rectangle {

    public Rectangle() {
        super();
    }

    @Override
    public Rectangle clone() {
        Rectangle clone = new Rectangle();

        clone.setX(this.getX());
        clone.setY(this.getY());
        clone.setWidth(this.getWidth());
        clone.setHeight(this.getHeight());

        clone.setFill(this.getFill());

        clone.setCursor(this.getCursor());
        clone.setOnMouseMoved(this.getOnMouseMoved());
        clone.setOnMousePressed(this.getOnMousePressed());
        clone.setOnMouseDragged(this.getOnMouseDragged());
        clone.setOnMouseReleased(this.getOnMouseReleased());

        return clone;
    }

}
