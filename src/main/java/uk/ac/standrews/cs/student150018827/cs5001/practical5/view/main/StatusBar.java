package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.MainController;

public class StatusBar extends BorderPane {

    private MainController mainController;

    private Label coordinatesLabel;

    public StatusBar(MainController mainController) {
        super();

        this.mainController = mainController;

        setPadding(new Insets(2, 2, 2, 2));

        coordinatesLabel = new Label();

        setRight(coordinatesLabel);
    }

    public void setCoordinatesLabel(int x, int y) {
        coordinatesLabel.setText("(" + x + ", " + y + ")");
    }

    public void clearCoordinatesLabel() {
        coordinatesLabel.setText(null);
    }

}