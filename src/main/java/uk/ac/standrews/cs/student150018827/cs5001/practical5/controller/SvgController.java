package uk.ac.standrews.cs.student150018827.cs5001.practical5.controller;

import javafx.scene.Node;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.objects.Ellipse;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.objects.Line;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.objects.Rectangle;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SvgController {

    public static final String SVG_NAMESPACE = "http://www.w3.org/2000/svg";

    private Document svgDocument;
    private Element svgElement;

    public SvgController() {
    }

    public void createSVGDocument() {
        svgElement = new Element("svg", SVG_NAMESPACE);

        svgDocument = new Document();
        svgDocument.setRootElement(svgElement);

        svgElement.setAttribute("version", "1.1");
    }

    public void setDimensions(int width, int height) {
        String widthString = String.valueOf(width);
        String heightString = String.valueOf(height);

        svgElement.setAttribute("width", widthString);
        svgElement.setAttribute("height", heightString);
        svgElement.setAttribute("viewBox", "0 0 " + widthString + " " + heightString);
    }

    public int getWidth() {
        return Integer.parseInt(svgElement.getAttributeValue("width"));
    }

    public int getHeight() {
        return Integer.parseInt(svgElement.getAttributeValue("height"));
    }

    public void setTitle(String title) {
        svgElement.setAttribute("id", title);
    }

    public String getTitle() {
        return svgElement.getAttributeValue("id");
    }

    public void setObjects(List<Node> objects) {
        for (Node object : objects) {
            Element element = null;

            if (object instanceof Rectangle) {
                element = ((Rectangle) object).getSvgElement();
            }

            if (object instanceof Ellipse) {
                element = ((Ellipse) object).getSvgElement();
            }

            if (object instanceof Line) {
                element = ((Line) object).getSvgElement();
            }

            if (element != null) {
                svgElement.addContent(element);
            }
        }
    }

    public List<Node> getObjects() {
        List<Node> objects = new ArrayList<>();

        for (Element element : svgElement.getChildren()) {
            Node object = null;
            String name = element.getName().toLowerCase().trim();

            switch (name) {
                case "rect": {
                    object = Rectangle.createFromSvg(element);
                    break;
                }
                case "ellipse": {
                    object = Ellipse.createFromSvg(element);
                    break;
                }
                case "line": {
                    object = Line.createFromSvg(element);
                    break;
                }
            }

            if (object != null) {
                objects.add(object);
            }
        }

        return objects;
    }

    public void input(File file) throws IOException, JDOMException {
        SAXBuilder saxBuilder = new SAXBuilder();

        svgDocument = saxBuilder.build(file);
        svgElement = svgDocument.getRootElement();
    }

    public void output(File file) throws IOException {
        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.setFormat(Format.getPrettyFormat());
        xmlOutputter.output(svgDocument, new FileWriter(file));
    }

    @Override
    public String toString() {
        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.setFormat(Format.getPrettyFormat());
        return xmlOutputter.outputString(svgDocument);
    }

}