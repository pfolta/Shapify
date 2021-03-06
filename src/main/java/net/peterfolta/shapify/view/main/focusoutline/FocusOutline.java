package net.peterfolta.shapify.view.main.focusoutline;

import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import net.peterfolta.shapify.controller.MainController;
import net.peterfolta.shapify.main.Data;
import net.peterfolta.shapify.model.objects.Image;
import net.peterfolta.shapify.model.objects.Rectangle;
import net.peterfolta.shapify.view.main.eventhandlers.ContextEventHandler;
import net.peterfolta.shapify.view.main.eventhandlers.SelectEventHandler;

import java.util.ArrayList;
import java.util.List;

public class FocusOutline {

    private MainController mainController;
    private Node selectedObject;

    private Rectangle focusRectangle;

    private List<ResizeAnchor> resizeAnchors;

    private RotateAnchor rotateAnchor;

    public FocusOutline(MainController mainController, Node object) {
        this.mainController = mainController;
        this.selectedObject = object;

        createFocusRectangle();
        createResizeAnchors();
        createRotateAnchor();
    }

    private void createFocusRectangle() {
        focusRectangle = new Rectangle();
        focusRectangle.setFocusOutline(true);
        focusRectangle.setStroke(Data.FOCUS_OUTLINE_COLOR);
        focusRectangle.setFill(Color.TRANSPARENT);
        focusRectangle.getStrokeDashArray().addAll(5.0, 5.0);
        focusRectangle.setStrokeWidth(Data.FOCUS_OUTLINE_STROKE_WIDTH);

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

            selectedEllipse.centerXProperty().bind(focusRectangle.xProperty().add(selectedEllipse.radiusXProperty()));
            selectedEllipse.centerYProperty().bind(focusRectangle.yProperty().add(selectedEllipse.radiusYProperty()));

            selectedEllipse.radiusXProperty().bind(focusRectangle.widthProperty().divide(2));
            selectedEllipse.radiusYProperty().bind(focusRectangle.heightProperty().divide(2));
        }

        if (selectedObject instanceof Line) {
            Line selectedLine = (Line) selectedObject;

            if (selectedLine.getStartX() < selectedLine.getEndX()) {
                focusRectangle.setX(selectedLine.getStartX());
                focusRectangle.setWidth(selectedLine.getEndX() - selectedLine.getStartX());

                selectedLine.startXProperty().bind(focusRectangle.xProperty());
                selectedLine.endXProperty().bind(focusRectangle.xProperty().add(focusRectangle.widthProperty()));
            } else {
                focusRectangle.setX(selectedLine.getEndX());
                focusRectangle.setWidth(selectedLine.getStartX() - selectedLine.getEndX());

                selectedLine.endXProperty().bind(focusRectangle.xProperty());
                selectedLine.startXProperty().bind(focusRectangle.xProperty().add(focusRectangle.widthProperty()));
            }

            if (selectedLine.getStartY() < selectedLine.getEndY()) {
                focusRectangle.setY(selectedLine.getStartY());
                focusRectangle.setHeight(selectedLine.getEndY() - selectedLine.getStartY());

                selectedLine.startYProperty().bind(focusRectangle.yProperty());
                selectedLine.endYProperty().bind(focusRectangle.yProperty().add(focusRectangle.heightProperty()));
            } else {
                focusRectangle.setY(selectedLine.getEndY());
                focusRectangle.setHeight(selectedLine.getStartY() - selectedLine.getEndY());

                selectedLine.endYProperty().bind(focusRectangle.yProperty());
                selectedLine.startYProperty().bind(focusRectangle.yProperty().add(focusRectangle.heightProperty()));
            }
        }

        if (selectedObject instanceof Image) {
            Image selectedImage = (Image) selectedObject;

            focusRectangle.setX(selectedImage.getX());
            focusRectangle.setY(selectedImage.getY());
            focusRectangle.setWidth(selectedImage.getFitWidth());
            focusRectangle.setHeight(selectedImage.getFitHeight());

            selectedImage.xProperty().bind(focusRectangle.xProperty());
            selectedImage.yProperty().bind(focusRectangle.yProperty());

            selectedImage.fitWidthProperty().bind(focusRectangle.widthProperty());
            selectedImage.fitHeightProperty().bind(focusRectangle.heightProperty());
        }

        // Apply rotation to focus outline
        if (selectedObject != null) {
            focusRectangle.getTransforms().addAll(selectedObject.getTransforms());
        }

        SelectEventHandler mouseEventHandler = new SelectEventHandler(mainController);
        ContextEventHandler contextEventHandler = new ContextEventHandler(mainController);

        focusRectangle.setOnMouseMoved(mouseEventHandler.getMouseMovedEventHandler());
        focusRectangle.setOnMousePressed(mouseEventHandler.getMousePressedOutlineEventHandler());
        focusRectangle.setOnMouseDragged(mouseEventHandler.getMouseDraggedEventHandler());
        focusRectangle.setOnMouseReleased(mouseEventHandler.getMouseReleasedEventHandler());

        focusRectangle.setOnContextMenuRequested(contextEventHandler);
    }

    public Rectangle getFocusRectangle() {
        return focusRectangle;
    }

    private void createResizeAnchors() {
        resizeAnchors = new ArrayList<>();

        ResizeAnchor nwResizeAnchor = new ResizeAnchor(mainController, Cursor.NW_RESIZE, focusRectangle);
        ResizeAnchor neResizeAnchor = new ResizeAnchor(mainController, Cursor.NE_RESIZE, focusRectangle);
        ResizeAnchor seResizeAnchor = new ResizeAnchor(mainController, Cursor.SE_RESIZE, focusRectangle);
        ResizeAnchor swResizeAnchor = new ResizeAnchor(mainController, Cursor.SW_RESIZE, focusRectangle);

        ResizeAnchor nResizeAnchor = new ResizeAnchor(mainController, Cursor.N_RESIZE, focusRectangle);
        ResizeAnchor eResizeAnchor = new ResizeAnchor(mainController, Cursor.E_RESIZE, focusRectangle);
        ResizeAnchor sResizeAnchor = new ResizeAnchor(mainController, Cursor.S_RESIZE, focusRectangle);
        ResizeAnchor wResizeAnchor = new ResizeAnchor(mainController, Cursor.W_RESIZE, focusRectangle);

        resizeAnchors.add(nwResizeAnchor);
        resizeAnchors.add(neResizeAnchor);
        resizeAnchors.add(seResizeAnchor);
        resizeAnchors.add(swResizeAnchor);

        resizeAnchors.add(nResizeAnchor);
        resizeAnchors.add(eResizeAnchor);
        resizeAnchors.add(sResizeAnchor);
        resizeAnchors.add(wResizeAnchor);
    }

    public List<ResizeAnchor> getResizeAnchors() {
        return resizeAnchors;
    }

    private void createRotateAnchor() {
        rotateAnchor = new RotateAnchor(mainController, focusRectangle);
    }

    public RotateAnchor getRotateAnchor() {
        return rotateAnchor;
    }

}