package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.focusoutline;

import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.MainController;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.eventhandlers.ContextEventHandler;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.eventhandlers.SelectEventHandler;

import java.util.ArrayList;
import java.util.List;

public class FocusOutline {

    private MainController mainController;
    private Node selectedObject;

    private Rectangle focusRectangle;

    private List<ResizeAnchor> resizeAnchors;

    public FocusOutline(MainController mainController, Node object) {
        this.mainController = mainController;
        this.selectedObject = object;

        createFocusRectangle();
        createResizeAnchors();
    }

    private void createFocusRectangle() {
        focusRectangle = new Rectangle();
        focusRectangle.setStroke(Color.RED);
        focusRectangle.setFill(Color.TRANSPARENT);
        focusRectangle.getStrokeDashArray().addAll(5.0, 5.0);
        focusRectangle.setStrokeWidth(2);

        focusRectangle.setCursor(Cursor.MOVE);

        if (selectedObject instanceof Rectangle) {
            Rectangle selectedRectangle = (Rectangle) selectedObject;

            focusRectangle.setX(selectedRectangle.getX());
            focusRectangle.setY(selectedRectangle.getY());
            focusRectangle.setWidth(selectedRectangle.getWidth());
            focusRectangle.setHeight(selectedRectangle.getHeight());

            selectedRectangle.xProperty().bind(focusRectangle.xProperty());
            selectedRectangle.yProperty().bind(focusRectangle.yProperty());

            selectedRectangle.widthProperty().bind(focusRectangle.widthProperty());
            selectedRectangle.heightProperty().bind(focusRectangle.heightProperty());
        }

        if (selectedObject instanceof Ellipse) {
            Ellipse selectedEllipse = (Ellipse) selectedObject;

            focusRectangle.setX(selectedEllipse.getCenterX() - selectedEllipse.getRadiusX());
            focusRectangle.setY(selectedEllipse.getCenterY() - selectedEllipse.getRadiusY());
            focusRectangle.setWidth(2 * selectedEllipse.getRadiusX());
            focusRectangle.setHeight(2 * selectedEllipse.getRadiusY());

            selectedEllipse.centerXProperty().bind(focusRectangle.xProperty().add(selectedEllipse.getRadiusX()));
            selectedEllipse.centerYProperty().bind(focusRectangle.yProperty().add(selectedEllipse.getRadiusY()));
        }

        SelectEventHandler mouseEventHandler = new SelectEventHandler(mainController);
        ContextEventHandler contextEventHandler = new ContextEventHandler(mainController);

        focusRectangle.setOnMouseMoved(mouseEventHandler.getMouseMovedEventHandler());
        focusRectangle.setOnMousePressed(mouseEventHandler.getMousePressedOutlineEventHandler());
        focusRectangle.setOnMouseDragged(mouseEventHandler.getMouseDraggedEventHandler());

        focusRectangle.setOnContextMenuRequested(contextEventHandler);
    }

    public Rectangle getFocusRectangle() {
        return focusRectangle;
    }

    private void createResizeAnchors() {
        resizeAnchors = new ArrayList<>();

        ResizeAnchor nwResizeAnchor = new ResizeAnchor(mainController, Cursor.NW_RESIZE, focusRectangle);
        ResizeAnchor neResizeAnchor = new ResizeAnchor(mainController, Cursor.NE_RESIZE, focusRectangle);
        ResizeAnchor swResizeAnchor = new ResizeAnchor(mainController, Cursor.SW_RESIZE, focusRectangle);
        ResizeAnchor seResizeAnchor = new ResizeAnchor(mainController, Cursor.SE_RESIZE, focusRectangle);

        resizeAnchors.add(nwResizeAnchor);
        resizeAnchors.add(neResizeAnchor);
        resizeAnchors.add(swResizeAnchor);
        resizeAnchors.add(seResizeAnchor);
    }

    public List<ResizeAnchor> getResizeAnchors() {
        return resizeAnchors;
    }

}