package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.eventhandlers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.MainController;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.objects.Rectangle;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.ArtBoard;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.focusoutline.RotateAnchor;

public class RotateEventHandler extends MouseEventHandler {

    private int mouseOriginalX;
    private int mouseOriginalY;

    private int originalX;
    private int originalY;

    private int originalWidth;
    private int originalHeight;

    private Rectangle focusRectangle;

    public RotateEventHandler(MainController mainController) {
        super(mainController);
    }

    @Override
    public EventHandler<MouseEvent> getMousePressedEventHandler() {
        return event -> {
            mouseOriginalX = (int) event.getX();
            mouseOriginalY = (int) event.getY();

            focusRectangle = mainController.getGUIController().getGuiState().getFocusOutline().getFocusRectangle();

            originalX = (int) focusRectangle.getX();
            originalY = (int) focusRectangle.getY();

            originalWidth = (int) focusRectangle.getWidth();
            originalHeight = (int) focusRectangle.getHeight();
        };
    }

    @Override
    public EventHandler<MouseEvent> getMouseDraggedEventHandler() {
        return event -> {
            int x = (int) event.getX();
            int y = (int) event.getY();

            ArtBoard artBoard = mainScene.getArtBoard();

            RotateAnchor rotateAnchor = (RotateAnchor) event.getSource();

            int deltaX = x - mouseOriginalX;
            int deltaY = y - mouseOriginalY;

            double rotation = focusRectangle.getRotate() + deltaX;
            rotation = rotation % 360;

            focusRectangle.setRotate(rotation);
        };
    }

    @Override
    public EventHandler<MouseEvent> getMouseReleasedEventHandler() {
        return null;
    }

}