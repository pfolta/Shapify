package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.MainController;

public class Banner extends BorderPane {

    private MainController mainController;

    private Label messageLabel;
    private Button closeButton;

    public Banner(MainController mainController, String message) {
        super();

        this.mainController = mainController;

        setPadding(new Insets(5, 5, 5, 5));
        this.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));

        messageLabel = new Label();
        messageLabel.setText(message);
        messageLabel.setStyle("-fx-font-weight: bold;");
        messageLabel.setMaxHeight(Double.MAX_VALUE);

        closeButton = new Button();
        closeButton.setText("X");
        closeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Banner.this.mainController.getGUIController().getMainWindow().getMainScene().hideBanner();
            }
        });

        setLeft(messageLabel);
        setRight(closeButton);
    }

}