package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.focusoutline;

import javafx.scene.Cursor;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.MainController;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.eventhandlers.MouseEventHandler;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.eventhandlers.ResizeEventHandler;

public class ResizeAnchor extends Rectangle {

    public static final int RESIZE_ANCHOR_DIMENSION = 8;

    private MainController mainController;

    public ResizeAnchor(MainController mainController, Cursor cursor, Rectangle focusRectangle) {
        super();

        this.mainController = mainController;

        setWidth(RESIZE_ANCHOR_DIMENSION);
        setHeight(RESIZE_ANCHOR_DIMENSION);

        setFill(FocusOutline.FOCUS_OUTLINE_COLOR);

        setCursor(cursor);

        MouseEventHandler resizeEventHandler = new ResizeEventHandler(mainController);
        setOnMousePressed(resizeEventHandler.getMousePressedEventHandler());
        setOnMouseDragged(resizeEventHandler.getMouseDraggedEventHandler());

        switch (cursor.toString()) {
            case "NW_RESIZE": {
                xProperty().bind(focusRectangle.xProperty().add(- RESIZE_ANCHOR_DIMENSION/2));
                yProperty().bind(focusRectangle.yProperty().add(- RESIZE_ANCHOR_DIMENSION/2));

                break;
            }
            case "NE_RESIZE": {
                xProperty().bind(focusRectangle.xProperty().add(focusRectangle.widthProperty().add(- RESIZE_ANCHOR_DIMENSION/2)));
                yProperty().bind(focusRectangle.yProperty().add(- RESIZE_ANCHOR_DIMENSION/2));

                break;
            }
            case "SW_RESIZE": {
                xProperty().bind(focusRectangle.xProperty().add(- RESIZE_ANCHOR_DIMENSION/2));
                yProperty().bind(focusRectangle.yProperty().add(focusRectangle.getHeight() - RESIZE_ANCHOR_DIMENSION/2));

                break;
            }
            case "SE_RESIZE": {
                xProperty().bind(focusRectangle.xProperty().add(focusRectangle.getWidth() - RESIZE_ANCHOR_DIMENSION/2));
                yProperty().bind(focusRectangle.yProperty().add(focusRectangle.getHeight() - RESIZE_ANCHOR_DIMENSION/2));

                break;
            }
        }
    }

}