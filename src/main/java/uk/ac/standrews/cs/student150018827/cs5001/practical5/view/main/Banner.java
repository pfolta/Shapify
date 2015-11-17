package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.MainController;

public class Banner extends BorderPane {

    private MainController mainController;

    private Label messageLabel;
    private Button closeButton;

    public Banner(MainController mainController, String message) {
        super();

        this.mainController = mainController;

        setPadding(new Insets(5, 5, 5, 5));
        getStyleClass().add("banner");

        messageLabel = new Label();
        messageLabel.setText(message);
        messageLabel.getStyleClass().add("bold");
        messageLabel.setMaxHeight(Double.MAX_VALUE);

        closeButton = new Button();
        closeButton.setText("X");
        closeButton.setOnAction(event -> Banner.this.mainController.getGUIController().getMainWindow().getMainScene().hideBanner());

        setLeft(messageLabel);
        setRight(closeButton);
    }

}