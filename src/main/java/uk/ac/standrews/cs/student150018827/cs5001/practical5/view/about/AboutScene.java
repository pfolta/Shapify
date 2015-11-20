package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.about;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AboutScene extends Scene {

    public AboutScene() {
        super(new VBox());

        VBox rootBox = new VBox();
        rootBox.setAlignment(Pos.CENTER);
        rootBox.setPadding(new Insets(10, 10, 10, 10));
        rootBox.setSpacing(5);

        getStylesheets().add(ClassLoader.getSystemResource("css/general.css").toExternalForm());

        Label aboutLabel = new Label();
        aboutLabel.setText("Drawing Program created by 150018827 as part of Practical 5");

        Label iconCreditsLabel = new Label();
        iconCreditsLabel.setText("Icons are taken from the \"Farm-Fresh Web Icons\" pack available at");

        Label iconLinkLabel = new Label();
        iconLinkLabel.setText("http://www.fatcow.com/free-icons");

        Button closeButton = new Button();
        closeButton.setText("Close");
        closeButton.setDefaultButton(true);
        closeButton.setCancelButton(true);
        closeButton.setOnAction(event -> ((Stage) AboutScene.this.getWindow()).close());

        rootBox.getChildren().addAll(
            aboutLabel,
            iconCreditsLabel,
                iconLinkLabel,
            closeButton
        );

        setRoot(rootBox);
    }

}