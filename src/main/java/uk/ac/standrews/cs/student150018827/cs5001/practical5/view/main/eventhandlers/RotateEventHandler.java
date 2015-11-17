package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.eventhandlers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.transform.Rotate;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.MainController;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.focusoutline.FocusOutline;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.focusoutline.ResizeAnchor;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.focusoutline.RotateAnchor;

public class RotateEventHandler extends MouseEventHandler {

    private int mouseOriginalX;
    private int mouseOriginalY;

    private double originalRotate;

    private FocusOutline focusOutline;

    public RotateEventHandler(MainController mainController) {
        super(mainController);
    }

    @Override
    public EventHandler<MouseEvent> getMouseExitedEventHandler() {
        return null;
    }

    @Override
    public EventHandler<MouseEvent> getMousePressedEventHandler() {
        return event -> {
            mouseOriginalX = (int) event.getX();
            mouseOriginalY = (int) event.getY();

            focusOutline = mainController.getGUIController().getGuiState().getFocusOutline();

            originalRotate = focusOutline.getFocusRectangle().getRotate();
        };
    }

    @Override
    public EventHandler<MouseEvent> getMouseDraggedEventHandler() {
        return event -> {
            int x = (int) event.getX();

            RotateAnchor rotateAnchor = (RotateAnchor) event.getSource();

            int delta = x - mouseOriginalX;

            Rotate rotation = new Rotate();
            rotation.setPivotX(focusOutline.getRotateAnchor().getCenterX());
            rotation.setPivotY(focusOutline.getRotateAnchor().getCenterY());
            rotation.setAngle(delta);

            focusOutline.getFocusRectangle().getTransforms().add(rotation);
            rotateAnchor.getTransforms().add(rotation);

            mainController.getGUIController().getGuiState().getSelectedObject().getTransforms().add(rotation);

            for (ResizeAnchor resizeAnchor : focusOutline.getResizeAnchors()) {
                resizeAnchor.getTransforms().add(rotation);
            }
        };
    }

    @Override
    public EventHandler<MouseEvent> getMouseReleasedEventHandler() {
        return null;
    }

}