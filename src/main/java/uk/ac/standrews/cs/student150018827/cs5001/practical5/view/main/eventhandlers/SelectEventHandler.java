package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.eventhandlers;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.MainController;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.ArtBoard;

public class SelectEventHandler extends MouseEventHandler {

    private int originalX;
    private int originalY;

    private int deltaX;
    private int deltaY;

    public SelectEventHandler(MainController mainController) {
        super(mainController);
    }

    public EventHandler<MouseEvent> getMousePressedEventHandler() {
        return event -> {
            originalX = (int) event.getX();
            originalY = (int) event.getY();

            Node object = (Node) event.getSource();

            if (object instanceof Rectangle) {
                Rectangle rectangle = (Rectangle) object;

                deltaX = (int) (originalX - rectangle.getX());
                deltaY = (int) (originalY - rectangle.getY());
            }

            if (object instanceof Ellipse) {
                Ellipse ellipse = (Ellipse) object;

                deltaX = (int) (originalX - ellipse.getCenterX());
                deltaY = (int) (originalY - ellipse.getCenterY());
            }

            if (object instanceof Line) {
                Line line = (Line) object;

                deltaX = (int) (originalX - line.getStartX());
                deltaY = (int) (originalY - line.getStartY());
            }
        };
    }

    public EventHandler<MouseEvent> getMouseDraggedEventHandler() {
        return event -> {
            ArtBoard artBoard = mainScene.getArtBoard();

            int x = (int) event.getX();
            int y = (int) event.getY();

            int xpos = x - deltaX;
            int ypos = y - deltaY;

            Node object = (Node) event.getSource();

            if (object instanceof Rectangle) {
                Rectangle rectangle = (Rectangle) object;

                // Ensure event is within artboard boundaries
                xpos = Math.max(xpos, 0);
                xpos = Math.min(xpos, (int) (artBoard.getWidth() - rectangle.getWidth()));

                ypos = Math.max(ypos, 0);
                ypos = Math.min(ypos, (int) (artBoard.getHeight() - rectangle.getHeight()));

                rectangle.setX(xpos);
                rectangle.setY(ypos);
            }

            if (object instanceof Ellipse) {
                Ellipse ellipse = (Ellipse) object;

                // Ensure event is within artboard boundaries
                xpos = Math.max(xpos, (int) (0 + ellipse.getRadiusX()));
                xpos = Math.min(xpos, (int) (artBoard.getWidth() - ellipse.getRadiusX()));

                ypos = Math.max(ypos, (int) (0 + ellipse.getRadiusY()));
                ypos = Math.min(ypos, (int) (artBoard.getHeight() - ellipse.getRadiusY()));

                ellipse.setCenterX(xpos);
                ellipse.setCenterY(ypos);
            }

            if (object instanceof Line) {
                Line line = (Line) object;

                line.setStartX(xpos);
                line.setStartY(ypos);

                line.setEndX(line.getEndX() - xpos);
                line.setEndY(line.getEndY() - ypos);
            }

            mainScene.getStatusBar().setCoordinatesLabel(x, y);
        };
    }

    public EventHandler<MouseEvent> getMouseReleasedEventHandler() {
        return event -> {

        };
    }

}