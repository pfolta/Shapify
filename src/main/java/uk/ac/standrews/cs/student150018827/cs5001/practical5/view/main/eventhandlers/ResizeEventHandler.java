package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.eventhandlers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import org.w3c.dom.css.Rect;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.MainController;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.ArtBoard;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.focusoutline.ResizeAnchor;

public class ResizeEventHandler extends MouseEventHandler {

    private int mouseOriginalX;
    private int mouseOriginalY;

    private int originalX;
    private int originalY;

    private int originalWidth;
    private int originalHeight;

    private Rectangle focusRectangle;

    public ResizeEventHandler(MainController mainController) {
        super(mainController);
    }

    @Override
    public EventHandler<MouseEvent> getMousePressedEventHandler() {
        return event -> {
            mouseOriginalX = (int) event.getX();
            mouseOriginalY = (int) event.getY();

            Rectangle rectangle = (Rectangle) event.getSource();

            focusRectangle = mainController.getGUIController().getGuiState().getFocusOutline().getFocusRectangle();

            originalX= (int) focusRectangle.getX();
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

            ResizeAnchor resizeAnchor = (ResizeAnchor) event.getSource();

            int deltaX = x - mouseOriginalX;
            int deltaY = y - mouseOriginalY;

            switch (resizeAnchor.getCursor().toString()) {
                case "NW_RESIZE": {
                    System.out.println("NW_RESIZE");

                    break;
                }
                case "NE_RESIZE": {
                    System.out.println("NE_RESIZE");

                    break;
                }
                case "SW_RESIZE": {
                    focusRectangle.setX(originalX + deltaX);
                    focusRectangle.setWidth(originalWidth - deltaX);

                    focusRectangle.setHeight(originalHeight + deltaY);

                    break;
                }
                case "SE_RESIZE": {
                    focusRectangle.setWidth(originalWidth + deltaX);
                    focusRectangle.setHeight(originalHeight + deltaY);

                    break;
                }
            }
        };
    }

    @Override
    public EventHandler<MouseEvent> getMouseReleasedEventHandler() {
        return null;
    }
}
