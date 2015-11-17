package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.MainController;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.Document;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.Observer;

public class StatusBar extends BorderPane implements Observer {

    private MainController mainController;

    private HBox hBox;
    private Label coordinatesLabel;
    private Label artBoardDimensionsLabel;

    public StatusBar(MainController mainController) {
        super();

        this.mainController = mainController;

        hBox = new HBox();

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

        hBox.getChildren().addAll(
                coordinatesLabel,
                artBoardDimensionsLabel
        );

        setLeft(hBox);
    }

    public void setCoordinatesLabel(int x, int y) {
        coordinatesLabel.setText("(" + x + ", " + y + ")");
    }

    @Override
    public void update() {
        Document document = mainController.getDocumentController().getDocument();

        if (document != null) {
            artBoardDimensionsLabel.setText(document.getWidth() + " x " + document.getHeight() + " px");
        }
    }
}