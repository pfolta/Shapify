package uk.ac.standrews.cs.student150018827.cs5001.practical5.util;

import javafx.scene.Node;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Shear;
import javafx.scene.transform.Transform;

import java.util.ArrayList;
import java.util.List;

public final class ShapeUtils {

    private ShapeUtils() {
    }

    public static String getTransforms(Node object) {
        String transforms = "";

        for (Transform transform : object.getTransforms()) {
            StringBuilder currentTransform = new StringBuilder();

            if (!transforms.isEmpty()) {
                currentTransform.append(" ");
            }

            if (transform instanceof Rotate) {
                Rotate rotate = (Rotate) transform;

                currentTransform.append("rotate(");
                currentTransform.append(rotate.getAngle());
                currentTransform.append(" ");
                currentTransform.append(rotate.getPivotX());
                currentTransform.append(" ");
                currentTransform.append(rotate.getPivotY());
                currentTransform.append(")");
            }

            if (transform instanceof Shear) {
                Shear shear = (Shear) transform;

                currentTransform.append("matrix(");
                currentTransform.append("1");
                currentTransform.append(", ");
                currentTransform.append(shear.getY());
                currentTransform.append(", ");
                currentTransform.append(shear.getX());
                currentTransform.append(", ");
                currentTransform.append("1");
                currentTransform.append(", ");
                currentTransform.append("0");
                currentTransform.append(", ");
                currentTransform.append("0");
                currentTransform.append(")");
            }

            transforms += currentTransform.toString();
        }

        return transforms;
    }

    public static List<Transform> getTransformsFromSvg(String svgTransform) {
        svgTransform = svgTransform.toLowerCase().trim();

        List<Transform> transforms = new ArrayList<>();

        String[] svgTransforms = svgTransform.split("\\)");

        for (String currentTransform : svgTransforms) {
            currentTransform = currentTransform.trim();

            Transform transform = null;

            if (currentTransform.startsWith("matrix")) {
                transform = new Shear();

                String[] properties = currentTransform.split(",");

                ((Shear) transform).setX(Double.parseDouble(properties[2].trim()));
                ((Shear) transform).setY(Double.parseDouble(properties[1].trim()));
            }

            if (currentTransform.startsWith("rotate")) {
                transform = new Rotate();

                String[] properties = currentTransform.split(" ");

                ((Rotate) transform).setAngle(Double.parseDouble(properties[0].replaceAll("rotate", "").replaceAll("\\(", "").trim()));
                ((Rotate) transform).setPivotX(Double.parseDouble(properties[1].trim()));
                ((Rotate) transform).setPivotY(Double.parseDouble(properties[2].trim()));
            }

            if (transform != null) {
                transforms.add(transform);
            }
        }

        return transforms;
    }

}