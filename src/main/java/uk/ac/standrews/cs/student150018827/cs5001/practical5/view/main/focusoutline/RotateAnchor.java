package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.focusoutline;

import javafx.scene.Cursor;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.MainController;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.main.Data;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.objects.Ellipse;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.objects.Rectangle;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.eventhandlers.MouseEventHandler;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.eventhandlers.RotateEventHandler;

public class RotateAnchor extends Ellipse {

    private MainController mainController;

    public RotateAnchor(MainController mainController, Rectangle focusRectangle) {
        super();

        this.mainController = mainController;

        setRadiusX(Data.RESIZE_ANCHOR_DIMENSION / 2);
        setRadiusY(Data.RESIZE_ANCHOR_DIMENSION / 2);

        setFill(Data.FOCUS_OUTLINE_COLOR);

        setCursor(Cursor.E_RESIZE);

        MouseEventHandler rotateEventHandler = new RotateEventHandler(mainController);
        setOnMousePressed(rotateEventHandler.getMousePressedEventHandler());
        setOnMouseDragged(rotateEventHandler.getMouseDraggedEventHandler());

        centerXProperty().bind(focusRectangle.xProperty().add(focusRectangle.widthProperty().divide(2)));
        centerYProperty().bind(focusRectangle.yProperty().add(focusRectangle.heightProperty().divide(2)));

        // Apply rotation to rotate anchor
        getTransforms().addAll(focusRectangle.getTransforms());
    }

}