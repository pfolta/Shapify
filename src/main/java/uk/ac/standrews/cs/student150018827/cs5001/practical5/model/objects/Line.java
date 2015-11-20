package uk.ac.standrews.cs.student150018827.cs5001.practical5.model.objects;

import javafx.scene.paint.Color;
import javafx.scene.transform.Transform;
import org.jdom2.Element;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.SvgController;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.util.ColorUtils;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.util.ShapeUtils;

import java.lang.reflect.MalformedParametersException;
import java.util.List;

public class Line extends javafx.scene.shape.Line implements CloneableNode {

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
        clone.setOnContextMenuRequested(this.getOnContextMenuRequested());

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

    public static Line createFromSvg(Element element) throws MalformedParametersException {
        if (!element.getName().toLowerCase().trim().equals("line")) {
            throw new MalformedParametersException("SVG Element is not a Line.");
        }

        Line line = new Line();

        line.setStartX(Double.parseDouble(element.getAttributeValue("x1")));
        line.setStartY(Double.parseDouble(element.getAttributeValue("y1")));
        line.setEndX(Double.parseDouble(element.getAttributeValue("x2")));
        line.setEndY(Double.parseDouble(element.getAttributeValue("y2")));

        line.setStroke(Color.web(element.getAttributeValue("stroke"), Double.parseDouble(element.getAttributeValue("stroke-opacity"))));
        line.setStrokeWidth(Double.parseDouble(element.getAttributeValue("stroke-width")));

        String svgTransforms = element.getAttributeValue("transform");

        if (svgTransforms != null) {
            List<Transform> transforms = ShapeUtils.getTransformsFromSvg(svgTransforms);
            line.getTransforms().addAll(transforms);
        }

        return line;
    }

}