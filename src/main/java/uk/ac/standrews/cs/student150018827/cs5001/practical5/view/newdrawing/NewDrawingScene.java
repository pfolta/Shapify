package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.newdrawing;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class NewDrawingScene {

    private Scene scene;

    private GridPane gridPane;

    private Label nameLabel;
    private TextField nameTextField;

    private Label widthLabel;
    private Spinner widthSpinner;

    private Label heightLabel;
    private Spinner heightSpinner;

    public NewDrawingScene() {
        gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));

        nameLabel = new Label();
        nameLabel.setText("Name: ");

        nameTextField = new TextField();

        widthLabel = new Label();
        widthLabel.setText("Width: ");

        widthSpinner = new Spinner();
        widthSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000));
        widthSpinner.setEditable(true);

        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameTextField, 1, 0);
        gridPane.add(widthLabel, 0, 1);
        gridPane.add(widthSpinner, 1, 1);

        scene = new Scene(gridPane, 300, 150);
    }

    public Scene getScene() {
        return scene;
    }

}