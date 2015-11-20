package uk.ac.standrews.cs.student150018827.cs5001.practical5.model.objects;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Transform;
import org.jdom2.Element;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.SvgController;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.util.ShapeUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.MalformedParametersException;
import java.util.Base64;
import java.util.List;

public class Image extends ImageView implements CloneableNode {

    @Override
    public Image clone() {
        try {
            super.clone();
        } catch (CloneNotSupportedException exception) {
            exception.addSuppressed(new CloneNotSupportedException());
        }

        Image clone = new Image();

        clone.setImage(getImageFromBase64(getBase64Encoding()));

        clone.setX(this.getX());
        clone.setY(this.getY());
        clone.setFitWidth(this.getFitWidth());
        clone.setFitHeight(this.getFitHeight());
        clone.getTransforms().addAll(this.getTransforms());

        clone.setCursor(this.getCursor());
        clone.setOnMouseMoved(this.getOnMouseMoved());
        clone.setOnMousePressed(this.getOnMousePressed());
        clone.setOnMouseDragged(this.getOnMouseDragged());
        clone.setOnMouseReleased(this.getOnMouseReleased());
        clone.setOnContextMenuRequested(this.getOnContextMenuRequested());

        return clone;
    }

    public Element getSvgElement() {
        Element element = new Element("image", SvgController.SVG_NAMESPACE);
        element.addNamespaceDeclaration(SvgController.XLINK_NAMESPACE);

        element.setAttribute("href", "data:image/png;base64," + getBase64Encoding(), SvgController.XLINK_NAMESPACE);

        element.setAttribute("x", String.valueOf(this.getX()));
        element.setAttribute("y", String.valueOf(this.getY()));
        element.setAttribute("width", String.valueOf(this.getFitWidth()));
        element.setAttribute("height", String.valueOf(this.getFitHeight()));
        element.setAttribute("preserveAspectRatio", "none");

        String transforms = ShapeUtils.getTransforms(this);

        if (!transforms.isEmpty()) {
            element.setAttribute("transform", transforms);
        }

        return element;
    }

    public static Image createFromSvg(Element element) throws MalformedParametersException {
        if (!element.getName().toLowerCase().trim().equals("image")) {
            throw new MalformedParametersException("SVG Element is not a Rectangle.");
        }

        Image image = new Image();

        image.setImage(getImageFromBase64(element.getAttributeValue("href", SvgController.XLINK_NAMESPACE)));

        image.setX(Double.parseDouble(element.getAttributeValue("x")));
        image.setY(Double.parseDouble(element.getAttributeValue("y")));
        image.setFitWidth(Double.parseDouble(element.getAttributeValue("width")));
        image.setFitHeight(Double.parseDouble(element.getAttributeValue("height")));

        String svgTransforms = element.getAttributeValue("transform");

        if (svgTransforms != null) {
            List<Transform> transforms = ShapeUtils.getTransformsFromSvg(svgTransforms);
            image.getTransforms().addAll(transforms);
        }

        return image;
    }

    private String getBase64Encoding() {
        String base64 = null;

        if (this.getImage() != null) {
            try {
                BufferedImage image = SwingFXUtils.fromFXImage(this.getImage(), null);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ImageIO.write(image, "png", byteArrayOutputStream);
                base64 = new String(Base64.getEncoder().encode(byteArrayOutputStream.toByteArray()));
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }

        return base64;
    }

    private static javafx.scene.image.Image getImageFromBase64(String base64) {
        base64 = base64.replace("data:image/png;base64,", "");

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Base64.getDecoder().decode(base64));
        return new javafx.scene.image.Image(byteArrayInputStream);
    }

}