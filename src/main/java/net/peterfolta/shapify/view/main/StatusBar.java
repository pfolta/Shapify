package net.peterfolta.shapify.view.main;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import net.peterfolta.shapify.controller.MainController;
import net.peterfolta.shapify.model.Document;
import net.peterfolta.shapify.model.Observer;
import net.peterfolta.shapify.model.GUIState;

public class StatusBar extends BorderPane implements Observer {

    private MainController mainController;

    private HBox leftHBox;
    private Label coordinatesLabel;
    private Label artBoardDimensionsLabel;

    private HBox rightHBox;
    private Label zoomLabel;

    public StatusBar(MainController mainController) {
        super();

        this.mainController = mainController;

        leftHBox = new HBox();

        coordinatesLabel = new Label();
        coordinatesLabel.setGraphic(new ImageView(new Image(ClassLoader.getSystemResourceAsStream("icons/16x16/derivatives.png"))));
        coordinatesLabel.getStyleClass().add("statusbar-label");
        coordinatesLabel.getStyleClass().add("border-right");
        coordinatesLabel.setMinWidth(150);

        artBoardDimensionsLabel = new Label();
        artBoardDimensionsLabel.setGraphic(new ImageView(new Image(ClassLoader.getSystemResourceAsStream("icons/16x16/canvas_size.png"))));
        artBoardDimensionsLabel.getStyleClass().add("statusbar-label");
        artBoardDimensionsLabel.getStyleClass().add("border-right");
        artBoardDimensionsLabel.setMinWidth(150);

        leftHBox.getChildren().addAll(
                coordinatesLabel,
                artBoardDimensionsLabel
        );

        rightHBox = new HBox();

        zoomLabel = new Label();
        zoomLabel.setGraphic(new ImageView(new Image(ClassLoader.getSystemResourceAsStream("icons/16x16/zoom.png"))));
        zoomLabel.getStyleClass().add("statusbar-label");
        zoomLabel.getStyleClass().add("border-left");
        zoomLabel.setMinWidth(150);

        rightHBox.getChildren().addAll(
                zoomLabel
        );

        setLeft(leftHBox);
        setRight(rightHBox);
    }

    public void setCoordinatesLabel(int x, int y) {
        coordinatesLabel.setText("(" + x + ", " + y + ")");
    }

    @Override
    public void update() {
        Document document = mainController.getDocumentController().getDocument();
        GUIState guiState = mainController.getGUIController().getGuiState();

        if (document != null) {
            artBoardDimensionsLabel.setText(document.getWidth() + " x " + document.getHeight() + " px");
        }

        int zoomLevel = (int) (guiState.getZoomLevel() * 100);
        zoomLabel.setText(zoomLevel + "%");
    }

    public void clear() {
        coordinatesLabel.setText("");
        artBoardDimensionsLabel.setText("");
        zoomLabel.setText("");
    }

}