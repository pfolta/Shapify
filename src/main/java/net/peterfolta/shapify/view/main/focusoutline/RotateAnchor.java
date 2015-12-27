package net.peterfolta.shapify.view.main.focusoutline;

import javafx.scene.Cursor;
import net.peterfolta.shapify.controller.MainController;
import net.peterfolta.shapify.model.objects.Ellipse;
import net.peterfolta.shapify.main.Data;
import net.peterfolta.shapify.model.objects.Rectangle;
import net.peterfolta.shapify.view.main.eventhandlers.MouseEventHandler;
import net.peterfolta.shapify.view.main.eventhandlers.RotateEventHandler;

public class RotateAnchor extends Ellipse {

    private MainController mainController;

    public RotateAnchor(MainController mainController, Rectangle focusRectangle) {
        super();

        this.mainController = mainController;

        setRadiusX(Data.RESIZE_ANCHOR_DIMENSION / 2);
        setRadiusY(Data.RESIZE_ANCHOR_DIMENSION / 2);

        setFill(Data.FOCUS_OUTLINE_COLOR);

        setCursor(Cursor.cursor(ClassLoader.getSystemResource("cur/rotate.png").toExternalForm()));

        MouseEventHandler rotateEventHandler = new RotateEventHandler(this.mainController);
        setOnMousePressed(rotateEventHandler.getMousePressedEventHandler());
        setOnMouseDragged(rotateEventHandler.getMouseDraggedEventHandler());
        setOnMouseReleased(rotateEventHandler.getMouseReleasedEventHandler());

        centerXProperty().bind(focusRectangle.xProperty().add(focusRectangle.widthProperty().divide(2)));
        centerYProperty().bind(focusRectangle.yProperty().add(focusRectangle.heightProperty().divide(2)));

        // Apply rotation to rotate anchor
        getTransforms().addAll(focusRectangle.getTransforms());
    }

}