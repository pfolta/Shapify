package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.focusoutline;

import javafx.scene.Cursor;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.MainController;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.eventhandlers.MouseEventHandler;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.eventhandlers.ResizeEventHandler;

public class ResizeAnchor extends Rectangle {

    MainController mainController;

    public ResizeAnchor(MainController mainController, Cursor cursor, Rectangle focusRectangle) {
        super();

        this.mainController = mainController;

        setWidth(10);
        setHeight(10);

        setFill(Color.RED);

        setCursor(cursor);

        MouseEventHandler resizeEventHandler = new ResizeEventHandler(mainController);
        setOnMousePressed(resizeEventHandler.getMousePressedEventHandler());
        setOnMouseDragged(resizeEventHandler.getMouseDraggedEventHandler());

        switch (cursor.toString()) {
            case "NW_RESIZE": {
                xProperty().bind(focusRectangle.xProperty().add(-5));
                yProperty().bind(focusRectangle.yProperty().add(-5));

                break;
            }
            case "NE_RESIZE": {
                xProperty().bind(focusRectangle.xProperty().add(focusRectangle.widthProperty().add(-5)));
                yProperty().bind(focusRectangle.yProperty().add(-5));

                break;
            }
            case "SW_RESIZE": {
                xProperty().bind(focusRectangle.xProperty().add(-5));
                yProperty().bind(focusRectangle.yProperty().add(focusRectangle.getHeight() -5));

                break;
            }
            case "SE_RESIZE": {
                xProperty().bind(focusRectangle.xProperty().add(focusRectangle.getWidth() - 5));
                yProperty().bind(focusRectangle.yProperty().add(focusRectangle.getHeight() -5));

                break;
            }
        }
    }

}