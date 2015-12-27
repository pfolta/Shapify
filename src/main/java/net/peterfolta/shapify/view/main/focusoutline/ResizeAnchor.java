package net.peterfolta.shapify.view.main.focusoutline;

import javafx.scene.Cursor;
import javafx.scene.paint.Color;
import net.peterfolta.shapify.controller.MainController;
import net.peterfolta.shapify.main.Data;
import net.peterfolta.shapify.model.objects.Rectangle;
import net.peterfolta.shapify.view.main.eventhandlers.MouseEventHandler;
import net.peterfolta.shapify.view.main.eventhandlers.ResizeEventHandler;

public class ResizeAnchor extends Rectangle {

    private MainController mainController;

    public ResizeAnchor(MainController mainController, Cursor cursor, Rectangle focusRectangle) {
        super();

        this.mainController = mainController;

        setWidth(Data.RESIZE_ANCHOR_DIMENSION);
        setHeight(Data.RESIZE_ANCHOR_DIMENSION);

        setFill(Color.WHITE);
        setStrokeWidth(2.0);
        setStroke(Data.FOCUS_OUTLINE_COLOR);

        setCursor(cursor);

        MouseEventHandler resizeEventHandler = new ResizeEventHandler(this.mainController);
        setOnMousePressed(resizeEventHandler.getMousePressedEventHandler());
        setOnMouseDragged(resizeEventHandler.getMouseDraggedEventHandler());
        setOnMouseReleased(resizeEventHandler.getMouseReleasedEventHandler());

        switch (cursor.toString()) {
            case "NW_RESIZE": {
                xProperty().bind(focusRectangle.xProperty().subtract(Data.RESIZE_ANCHOR_DIMENSION / 2));
                yProperty().bind(focusRectangle.yProperty().subtract(Data.RESIZE_ANCHOR_DIMENSION / 2));

                break;
            }
            case "NE_RESIZE": {
                xProperty().bind(focusRectangle.xProperty().add(focusRectangle.widthProperty()).subtract(Data.RESIZE_ANCHOR_DIMENSION / 2));
                yProperty().bind(focusRectangle.yProperty().subtract(Data.RESIZE_ANCHOR_DIMENSION/2));

                break;
            }
            case "SE_RESIZE": {
                xProperty().bind(focusRectangle.xProperty().add(focusRectangle.widthProperty()).subtract(Data.RESIZE_ANCHOR_DIMENSION / 2));
                yProperty().bind(focusRectangle.yProperty().add(focusRectangle.heightProperty()).subtract(Data.RESIZE_ANCHOR_DIMENSION / 2));

                break;
            }
            case "SW_RESIZE": {
                xProperty().bind(focusRectangle.xProperty().subtract(Data.RESIZE_ANCHOR_DIMENSION/2));
                yProperty().bind(focusRectangle.yProperty().add(focusRectangle.heightProperty()).subtract(Data.RESIZE_ANCHOR_DIMENSION / 2));

                break;
            }

            case "N_RESIZE": {
                xProperty().bind(focusRectangle.xProperty().add(focusRectangle.widthProperty().divide(2)).subtract(Data.RESIZE_ANCHOR_DIMENSION / 2));
                yProperty().bind(focusRectangle.yProperty().subtract(Data.RESIZE_ANCHOR_DIMENSION / 2));

                break;
            }
            case "E_RESIZE": {
                xProperty().bind(focusRectangle.xProperty().add(focusRectangle.widthProperty()).subtract(Data.RESIZE_ANCHOR_DIMENSION / 2));
                yProperty().bind(focusRectangle.yProperty().add(focusRectangle.heightProperty().divide(2)).subtract(Data.RESIZE_ANCHOR_DIMENSION / 2));

                break;
            }
            case "S_RESIZE": {
                xProperty().bind(focusRectangle.xProperty().add(focusRectangle.widthProperty().divide(2)).subtract(Data.RESIZE_ANCHOR_DIMENSION / 2));
                yProperty().bind(focusRectangle.yProperty().add(focusRectangle.heightProperty()).subtract(Data.RESIZE_ANCHOR_DIMENSION / 2));

                break;
            }
            case "W_RESIZE": {
                xProperty().bind(focusRectangle.xProperty().subtract(Data.RESIZE_ANCHOR_DIMENSION / 2));
                yProperty().bind(focusRectangle.yProperty().add(focusRectangle.heightProperty().divide(2)).subtract(Data.RESIZE_ANCHOR_DIMENSION / 2));

                break;
            }
        }

        // Apply rotation to resize anchors
        getTransforms().addAll(focusRectangle.getTransforms());
    }

}