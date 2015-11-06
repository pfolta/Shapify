package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.eventhandlers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
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

            ResizeAnchor resizeAnchor = (ResizeAnchor) event.getSource();

            int deltaX = x - mouseOriginalX;
            int deltaY = y - mouseOriginalY;

            switch (resizeAnchor.getCursor().toString()) {
                case "NW_RESIZE": {
                    focusRectangle.setX(originalX + deltaX);
                    focusRectangle.setWidth(originalWidth - deltaX);

                    focusRectangle.setY(originalY + deltaY);
                    focusRectangle.setHeight(originalHeight - deltaY);

                    break;
                }
                case "NE_RESIZE": {
                    focusRectangle.setWidth(originalWidth + deltaX);

                    focusRectangle.setY(originalY + deltaY);
                    focusRectangle.setHeight(originalHeight - deltaY);

                    break;
                }
                case "SW_RESIZE": {
                    focusRectangle.setX(originalX + deltaX);
                    focusRectangle.setWidth(originalWidth - deltaX);

                    focusRectangle.setHeight(originalHeight + deltaY);

                    break;
                }
                case "SE_RESIZE": {
                    int width = originalWidth + deltaX;
                    int height = originalHeight + deltaY;

                    width = Math.max(width, 1);
                    width = Math.min(width, (int) (artBoard.getWidth() - focusRectangle.getX()));

                    height = Math.max(height, 1);
                    height = Math.min(height, (int) (artBoard.getHeight() - focusRectangle.getY()));

                    focusRectangle.setWidth(width);
                    focusRectangle.setHeight(height);

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
