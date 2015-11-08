package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.exportbitmap;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.MainController;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.Document;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.eventhandlers.KeyEventHandler;

import java.util.InputMismatchException;

public class ExportBitmapScene extends Scene {

    private MainController mainController;

    private Document document;

    private GridPane gridPane;

    private Label widthLabel;
    private TextField widthTextField;
    private Label widthUnitLabel;

    private Label heightLabel;
    private TextField heightTextField;
    private Label heightUnitLabel;

    private Button exportButton;
    private Button cancelButton;

    public ExportBitmapScene(MainController mainController) {
        super(new VBox());

        this.mainController = mainController;

        document = mainController.getDocumentController().getDocument();

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10, 10, 10, 10));

        gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        widthLabel = new Label();
        widthLabel.setText("Width: ");
        widthLabel.setTextAlignment(TextAlignment.RIGHT);

        widthTextField = new TextField();
        widthTextField.setText(String.valueOf(document.getWidth()));
        widthTextField.setOnKeyReleased(getWidthEventHandler());
        widthTextField.setOnMouseReleased(getWidthEventHandler());

        widthUnitLabel = new Label();
        widthUnitLabel.setText("px");

        heightLabel = new Label();
        heightLabel.setText("Height: ");
        heightLabel.setTextAlignment(TextAlignment.RIGHT);

        heightTextField = new TextField();
        heightTextField.setText(String.valueOf(document.getHeight()));
        heightTextField.setOnKeyReleased(getHeightEventHandler());
        heightTextField.setOnMouseReleased(getHeightEventHandler());

        heightUnitLabel = new Label();
        heightUnitLabel.setText("px");

        HBox hbox = new HBox(10);
        hbox.setPadding(new Insets(10, 0, 0, 0));
        hbox.setAlignment(Pos.BOTTOM_RIGHT);

        exportButton = new Button();
        exportButton.setText("Export Bitmap");
        exportButton.setDefaultButton(true);
        exportButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                exportBitmap();
            }
        });

        cancelButton = new Button();
        cancelButton.setText("Cancel");
        cancelButton.setCancelButton(true);
        cancelButton.setOnAction(event -> close());

        hbox.getChildren().addAll(exportButton, cancelButton);

        gridPane.add(widthLabel, 0, 0, 1, 1);
        gridPane.add(widthTextField, 1, 0, 1, 1);
        gridPane.add(widthUnitLabel, 2, 0, 1, 1);
        gridPane.add(heightLabel, 0, 1, 1, 1);
        gridPane.add(heightTextField, 1, 1, 1, 1);
        gridPane.add(heightUnitLabel, 2, 1, 1, 1);

        vbox.getChildren().add(gridPane);
        vbox.getChildren().add(hbox);

        setRoot(vbox);
    }

    public EventHandler getWidthEventHandler() {
        return event -> {
            try {
                int originalWidth = document.getWidth();
                int originalHeight = document.getHeight();

                int width = Integer.parseInt(widthTextField.getText());

                double scaleFactor = (double) width / (double) originalWidth;

                heightTextField.setText(String.valueOf((int) (scaleFactor * originalHeight)));
            } catch (NumberFormatException exception) {
            }
        };
    }

    public EventHandler getHeightEventHandler() {
        return event -> {
            try {
                int originalWidth = document.getWidth();
                int originalHeight = document.getHeight();

                int height = Integer.parseInt(heightTextField.getText());

                double scaleFactor = (double) height / (double) originalHeight;

                widthTextField.setText(String.valueOf((int) (scaleFactor * originalWidth)));
            } catch (NumberFormatException exception) {
            }
        };
    }

    private void exportBitmap() {
        try {
            validateInputs();

            int originalWidth = document.getWidth();
            int width = Integer.parseInt(widthTextField.getText());

            double scaleFactor = (double) width / (double) originalWidth;

            if (mainController.getGUIController().exportBitmap((Stage) this.getWindow(), scaleFactor)) {
                close();
            }
        } catch (InputMismatchException exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(getWindow());
            alert.setContentText(exception.getMessage());

            alert.showAndWait();
        }
    }

    private void validateInputs() throws InputMismatchException {
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
        Stage stage = (Stage) ExportBitmapScene.this.getWindow();
        stage.close();
    }

}