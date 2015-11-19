package uk.ac.standrews.cs.student150018827.cs5001.practical5.model.objects;

import javafx.scene.paint.Color;
import javafx.scene.transform.Transform;
import org.jdom2.Element;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.SvgController;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.util.ColorUtils;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.util.ShapeUtils;

import java.util.List;

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

    public Element getSvgElement() {
        Element element = new Element("ellipse", SvgController.SVG_NAMESPACE);

        element.setAttribute("cx", String.valueOf(this.getCenterX()));
        element.setAttribute("cy", String.valueOf(this.getCenterY()));
        element.setAttribute("rx", String.valueOf(this.getRadiusX()));
        element.setAttribute("ry", String.valueOf(this.getRadiusY()));

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

    public void createFromSvg(Element element) {
        this.setCenterX(Double.parseDouble(element.getAttributeValue("cx")));
        this.setCenterY(Double.parseDouble(element.getAttributeValue("cy")));
        this.setRadiusX(Double.parseDouble(element.getAttributeValue("rx")));
        this.setRadiusY(Double.parseDouble(element.getAttributeValue("ry")));

        this.setFill(Color.web(element.getAttributeValue("fill"), Double.parseDouble(element.getAttributeValue("fill-opacity"))));

        this.setStroke(Color.web(element.getAttributeValue("stroke"), Double.parseDouble(element.getAttributeValue("stroke-opacity"))));
        this.setStrokeWidth(Double.parseDouble(element.getAttributeValue("stroke-width")));

        String svgTransforms = element.getAttributeValue("transform");

        if (svgTransforms != null) {
            List<Transform> transforms = ShapeUtils.getTransformsFromSvg(svgTransforms);
            this.getTransforms().addAll(transforms);
        }
    }

}