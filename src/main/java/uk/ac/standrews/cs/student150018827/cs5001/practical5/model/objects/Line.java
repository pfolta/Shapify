package uk.ac.standrews.cs.student150018827.cs5001.practical5.model.objects;

import javafx.scene.paint.Color;
import org.jdom2.Element;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.SvgController;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.util.ColorUtils;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.util.ShapeUtils;

public class Line extends javafx.scene.shape.Line implements CloneableNode {

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

    public Element getSvgElement() {
        Element element = new Element("line", SvgController.SVG_NAMESPACE);

        element.setAttribute("x1", String.valueOf(this.getStartX()));
        element.setAttribute("y1", String.valueOf(this.getStartY()));
        element.setAttribute("x2", String.valueOf(this.getEndX()));
        element.setAttribute("y2", String.valueOf(this.getEndY()));

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

}