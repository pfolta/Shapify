package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.eventhandlers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.MainController;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.objects.Rectangle;
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
    public EventHandler<MouseEvent> getMouseExitedEventHandler() {
        return null;
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

            ResizeAnchor resizeAnchor = (ResizeAnchor) event.getSource();

            int deltaX = x - mouseOriginalX;
            int deltaY = y - mouseOriginalY;

            switch (resizeAnchor.getCursor().toString()) {
                case "NW_RESIZE": {
                    int xpos = originalX + deltaX;
                    xpos = Math.min(xpos, originalX + originalWidth - 1);
                    focusRectangle.setX(xpos);

                    int width = (originalX + originalWidth) - xpos;
                    focusRectangle.setWidth(width);

                    int ypos = originalY + deltaY;
                    ypos = Math.min(ypos, originalY + originalHeight - 1);
                    focusRectangle.setY(ypos);

                    int height = (originalY + originalHeight) - ypos;
                    focusRectangle.setHeight(height);

                    break;
                }
                case "NE_RESIZE": {
                    int width = originalWidth + deltaX;
                    width = Math.max(width, 1);
                    focusRectangle.setWidth(width);

                    int ypos = originalY + deltaY;
                    ypos = Math.min(ypos, originalY + originalHeight - 1);
                    focusRectangle.setY(ypos);

                    int height = (originalY + originalHeight) - ypos;
                    focusRectangle.setHeight(height);

                    break;
                }
                case "SE_RESIZE": {
                    int width = originalWidth + deltaX;
                    width = Math.max(width, 1);
                    focusRectangle.setWidth(width);

                    int height = originalHeight + deltaY;
                    height = Math.max(height, 1);
                    focusRectangle.setHeight(height);

                    break;
                }
                case "SW_RESIZE": {
                    int xpos = originalX + deltaX;
                    xpos = Math.min(xpos, originalX + originalWidth - 1);
                    focusRectangle.setX(xpos);

                    int width = (originalX + originalWidth) - xpos;
                    focusRectangle.setWidth(width);

                    int height = originalHeight + deltaY;
                    height = Math.max(height, 1);
                    focusRectangle.setHeight(height);

                    break;
                }

                case "N_RESIZE": {
                    int ypos = originalY + deltaY;
                    ypos = Math.min(ypos, originalY + originalHeight - 1);
                    focusRectangle.setY(ypos);

                    int height = (originalY + originalHeight) - ypos;
                    focusRectangle.setHeight(height);

                    break;
                }
                case "E_RESIZE": {
                    int width = originalWidth + deltaX;
                    width = Math.max(width, 1);
                    focusRectangle.setWidth(width);

                    break;
                }
                case "S_RESIZE": {
                    int height = originalHeight + deltaY;
                    height = Math.max(height, 1);
                    focusRectangle.setHeight(height);

                    break;
                }
                case "W_RESIZE": {
                    int xpos = originalX + deltaX;
                    xpos = Math.min(xpos, originalX + originalWidth - 1);
                    focusRectangle.setX(xpos);

                    int width = (originalX + originalWidth) - xpos;
                    focusRectangle.setWidth(width);

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