package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main;

import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.MainController;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.eventhandlers.ContextEventHandler;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.eventhandlers.KeyEventHandler;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.eventhandlers.MouseEventHandler;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.eventhandlers.SelectEventHandler;

public class FocusOutline {

    private MainController mainController;
    private Node selectedObject;

    private Rectangle focusRectangle;

    public FocusOutline(MainController mainController, Node object) {
        this.mainController = mainController;
        this.selectedObject = object;

        createFocusRectangle();
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

}