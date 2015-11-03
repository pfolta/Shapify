package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.newdrawing;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.GUIUtils;

import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.function.UnaryOperator;

public class NewDrawingScene {

    private Scene scene;

    private GridPane gridPane;

    private Label nameLabel;
    private TextField nameTextField;

    private Label widthLabel;
    private Spinner widthSpinner;
    private Label widthUnitLabel;

    private Label heightLabel;
    private Spinner heightSpinner;
    private Label heightUnitLabel;

    private Button okButton;
    private Button cancelButton;

    public NewDrawingScene() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10, 10, 10, 10));

        gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        nameLabel = new Label();
        nameLabel.setText("Name: ");
        nameLabel.setTextAlignment(TextAlignment.RIGHT);

        nameTextField = new TextField();
        nameTextField.setText("Untitled");

        widthLabel = new Label();
        widthLabel.setText("Width: ");
        widthLabel.setTextAlignment(TextAlignment.RIGHT);

        widthSpinner = new Spinner();
        widthSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10000));
        widthSpinner.setEditable(true);
        widthSpinner.getEditor().setTextFormatter(GUIUtils.getIntegerTextFormatter(10000));

        widthUnitLabel = new Label();
        widthUnitLabel.setText("px");

        heightLabel = new Label();
        heightLabel.setText("Height: ");
        heightLabel.setTextAlignment(TextAlignment.RIGHT);

        heightSpinner = new Spinner();
        heightSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10000));
        heightSpinner.setEditable(true);
        heightSpinner.getEditor().setTextFormatter(GUIUtils.getIntegerTextFormatter(10000));

        heightUnitLabel = new Label();
        heightUnitLabel.setText("px");

        HBox hbox = new HBox(10);
        hbox.setPadding(new Insets(10, 0, 0, 0));
        hbox.setAlignment(Pos.BOTTOM_RIGHT);

        okButton = new Button();
        okButton.setText("OK");
        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("OK Clicked!");
            }
        });

        cancelButton = new Button();
        cancelButton.setText("Cancel");
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) NewDrawingScene.this.getScene().getWindow();
                stage.close();
            }
        });

        hbox.getChildren().addAll(okButton, cancelButton);

        gridPane.add(nameLabel, 0, 0, 1, 1);
        gridPane.add(nameTextField, 1, 0, 2, 1);
        gridPane.add(widthLabel, 0, 1, 1, 1);
        gridPane.add(widthSpinner, 1, 1, 1, 1);
        gridPane.add(widthUnitLabel, 2, 1, 1, 1);
        gridPane.add(heightLabel, 0, 2, 1, 1);
        gridPane.add(heightSpinner, 1, 2, 1, 1);
        gridPane.add(heightUnitLabel, 2, 2, 1, 1);

        vbox.getChildren().add(gridPane);
        vbox.getChildren().add(hbox);

        scene = new Scene(vbox);
    }

    public Scene getScene() {
        return scene;
    }

}