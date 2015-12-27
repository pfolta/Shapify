package net.peterfolta.shapify.view.main;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import net.peterfolta.shapify.controller.MainController;

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
        closeButton.setGraphic(new ImageView(new Image(ClassLoader.getSystemResourceAsStream("icons/16x16/cross.png"))));
        closeButton.setTooltip(new Tooltip("Close"));
        closeButton.setOnAction(event -> Banner.this.mainController.getGUIController().getMainWindow().getMainScene().hideBanner());

        setLeft(messageLabel);
        setRight(closeButton);
    }

}