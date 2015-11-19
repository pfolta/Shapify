package uk.ac.standrews.cs.student150018827.cs5001.practical5.model.objects;

import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Shear;
import javafx.scene.transform.Transform;
import org.jdom2.Element;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.SvgController;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.util.ColorUtils;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.util.ShapeUtils;

public class Rectangle extends javafx.scene.shape.Rectangle implements CloneableNode {

    private boolean focusOutline;

    public Rectangle() {
        super();

        focusOutline = false;
    }

    @Override
    public Rectangle clone() {
        Rectangle clone = new Rectangle();

        clone.setX(this.getX());
        clone.setY(this.getY());
        clone.setWidth(this.getWidth());
        clone.setHeight(this.getHeight());
        clone.getTransforms().addAll(this.getTransforms());

        clone.setFill(this.getFill());

        clone.setCursor(this.getCursor());
        clone.setOnMouseMoved(this.getOnMouseMoved());
        clone.setOnMousePressed(this.getOnMousePressed());
        clone.setOnMouseDragged(this.getOnMouseDragged());
        clone.setOnMouseReleased(this.getOnMouseReleased());

        return clone;
    }

    public Element getSvgElement() {
        Element element = new Element("rect", SvgController.SVG_NAMESPACE);

        element.setAttribute("width", String.valueOf(this.getWidth()));
        element.setAttribute("height", String.valueOf(this.getHeight()));
        element.setAttribute("x", String.valueOf(this.getX()));
        element.setAttribute("y", String.valueOf(this.getY()));

        Color fillColor = (Color) this.getFill();
        element.setAttribute("fill", ColorUtils.getHexColor(fillColor));
        element.setAttribute("fill-opacity", String.valueOf(fillColor.getOpacity()));

        Color strokeColor = (Color) this.getStroke();
        element.setAttribute("stroke", ColorUtils.getHexColor(strokeColor));
        element.setAttribute("stroke-opacity", String.valueOf(strokeColor.getOpacity()));

        element.setAttribute("stroke-width", String.valueOf(this.getStrokeWidth()));

        String transforms = ShapeUtils.getTransforms(this);

        if (!transforms.isEmpty()) {
            element.setAttribute("transform", transforms);
        }

        return element;
    }

    public void setFocusOutline(boolean focusOutline) {
        this.focusOutline = focusOutline;
    }

    public boolean isFocusOutline() {
        return focusOutline;
    }

}
