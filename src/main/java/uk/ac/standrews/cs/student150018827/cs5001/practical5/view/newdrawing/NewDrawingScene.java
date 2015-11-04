package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.newdrawing;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.MainController;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.GUIUtils;

import java.util.InputMismatchException;

public class NewDrawingScene extends Scene {

    private MainController mainController;

    private GridPane gridPane;

    private Label nameLabel;
    private TextField nameTextField;

    private Label widthLabel;
    private TextField widthTextField;
    private Label widthUnitLabel;

    private Label heightLabel;
    private TextField heightTextField;
    private Label heightUnitLabel;

    private Button okButton;
    private Button cancelButton;

    public NewDrawingScene(MainController mainController) {
        super(new VBox());

        this.mainController = mainController;

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

        widthTextField = new TextField();
        widthTextField.setText("500");

        widthUnitLabel = new Label();
        widthUnitLabel.setText("px");

        heightLabel = new Label();
        heightLabel.setText("Height: ");
        heightLabel.setTextAlignment(TextAlignment.RIGHT);

        heightTextField = new TextField();
        heightTextField.setText("500");

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
                createNewDocument();
            }
        });

        cancelButton = new Button();
        cancelButton.setText("Cancel");
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                close();
            }
        });

        hbox.getChildren().addAll(okButton, cancelButton);

        gridPane.add(nameLabel, 0, 0, 1, 1);
        gridPane.add(nameTextField, 1, 0, 2, 1);
        gridPane.add(widthLabel, 0, 1, 1, 1);
        gridPane.add(widthTextField, 1, 1, 1, 1);
        gridPane.add(widthUnitLabel, 2, 1, 1, 1);
        gridPane.add(heightLabel, 0, 2, 1, 1);
        gridPane.add(heightTextField, 1, 2, 1, 1);
        gridPane.add(heightUnitLabel, 2, 2, 1, 1);

        vbox.getChildren().add(gridPane);
        vbox.getChildren().add(hbox);

        setRoot(vbox);
    }

    private void createNewDocument() {
        try {
            validateInputs();

            String name = nameTextField.getText();
            int width = Integer.parseInt(widthTextField.getText());
            int height = Integer.parseInt(heightTextField.getText());

            System.out.println("Create new document with name '" + name + "', width = " + width + "px and height = " + height + "px");

            mainController.getGUIController().getMainWindow().getMainScene().setArtBoard(width, height);

            close();
        } catch (InputMismatchException exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(exception.getMessage());

            alert.showAndWait();
        }
    }

    private void validateInputs() throws InputMismatchException {
        String name = nameTextField.getText();

        if (name.isEmpty()) {
            throw new InputMismatchException("Please specify a name.");
        }

        try {
            int width = Integer.parseInt(widthTextField.getText());
            int height = Integer.parseInt(heightTextField.getText());

            if (width < 1 || width > 10000) {
                throw new NumberFormatException();
            }

            if (height < 1 || height > 10000) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException exception) {
            throw new InputMismatchException("The values for width and height must be integer numbers between 1 and 10000.");
        }
    }

    private void close() {
        Stage stage = (Stage) NewDrawingScene.this.getWindow();
        stage.close();
    }

}