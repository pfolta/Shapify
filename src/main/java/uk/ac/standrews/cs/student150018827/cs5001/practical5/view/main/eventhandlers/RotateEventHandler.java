package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.eventhandlers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.transform.Rotate;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.MainController;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.objects.Rectangle;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.ArtBoard;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.focusoutline.FocusOutline;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.focusoutline.ResizeAnchor;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.focusoutline.RotateAnchor;

public class RotateEventHandler extends MouseEventHandler {

    private int mouseOriginalX;
    private int mouseOriginalY;

    private int originalX;
    private int originalY;

    private double originalRotate;

    private FocusOutline focusOutline;

    public RotateEventHandler(MainController mainController) {
        super(mainController);
    }

    @Override
    public EventHandler<MouseEvent> getMousePressedEventHandler() {
        return event -> {
            mouseOriginalX = (int) event.getX();
            mouseOriginalY = (int) event.getY();

            focusOutline = mainController.getGUIController().getGuiState().getFocusOutline();

            originalX = (int) focusOutline.getFocusRectangle().getX();
            originalY = (int) focusOutline.getFocusRectangle().getY();

            originalRotate = focusOutline.getFocusRectangle().getRotate();
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

            double rotation = originalRotate + deltaX;
            rotation = rotation % 360;

            Rotate anchorRotation = new Rotate();
            anchorRotation.setPivotX(focusOutline.getRotateAnchor().getCenterX());
            anchorRotation.setPivotY(focusOutline.getRotateAnchor().getCenterY());
            anchorRotation.setAngle(anchorRotation.getAngle() + deltaX);

            //focusOutline.getFocusRectangle().setRotate(rotation);
            focusOutline.getFocusRectangle().getTransforms().add(anchorRotation);
            focusOutline.getRotateAnchor().getTransforms().add(anchorRotation);

            mainController.getGUIController().getGuiState().getSelectedObject().getTransforms().add(anchorRotation);

            for (ResizeAnchor resizeAnchor : focusOutline.getResizeAnchors()) {
                resizeAnchor.getTransforms().add(anchorRotation);
            }
        };
    }

    @Override
    public EventHandler<MouseEvent> getMouseReleasedEventHandler() {
        return null;
    }

}