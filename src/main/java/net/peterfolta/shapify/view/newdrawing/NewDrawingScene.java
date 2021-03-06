package net.peterfolta.shapify.view.newdrawing;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import net.peterfolta.shapify.controller.MainController;
import net.peterfolta.shapify.model.Document;
import net.peterfolta.shapify.model.GUIState;
import net.peterfolta.shapify.view.DrawTools;

import java.util.InputMismatchException;

public class NewDrawingScene extends Scene {

    private MainController mainController;

    private GridPane gridPane;

    private Label titleLabel;
    private TextField titleTextField;

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

        getStylesheets().add(ClassLoader.getSystemResource("css/general.css").toExternalForm());

        this.mainController = mainController;

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10, 10, 10, 10));

        gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        titleLabel = new Label();
        titleLabel.setText("Title: ");
        titleLabel.setTextAlignment(TextAlignment.RIGHT);

        titleTextField = new TextField();
        titleTextField.setText("Untitled");

        widthLabel = new Label();
        widthLabel.setText("Width: ");
        widthLabel.setTextAlignment(TextAlignment.RIGHT);

        widthTextField = new TextField();
        widthTextField.setText("750");

        widthUnitLabel = new Label();
        widthUnitLabel.setText("px");

        heightLabel = new Label();
        heightLabel.setText("Height: ");
        heightLabel.setTextAlignment(TextAlignment.RIGHT);

        heightTextField = new TextField();
        heightTextField.setText("750");

        heightUnitLabel = new Label();
        heightUnitLabel.setText("px");

        HBox hbox = new HBox(10);
        hbox.setPadding(new Insets(10, 0, 0, 0));
        hbox.setAlignment(Pos.BOTTOM_RIGHT);

        okButton = new Button();
        okButton.setText("OK");
        okButton.setDefaultButton(true);
        okButton.setOnAction(event -> createNewDocument());

        cancelButton = new Button();
        cancelButton.setText("Cancel");
        cancelButton.setCancelButton(true);
        cancelButton.setOnAction(event -> close());

        hbox.getChildren().addAll(okButton, cancelButton);

        gridPane.add(titleLabel, 0, 0, 1, 1);
        gridPane.add(titleTextField, 1, 0, 2, 1);
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

            String title = titleTextField.getText();
            int width = Integer.parseInt(widthTextField.getText());
            int height = Integer.parseInt(heightTextField.getText());

            Document document = mainController.getDocumentController().createDocument(title, width, height);
            GUIState guiState = mainController.getGUIController().getGuiState();

            document.registerObserver(mainController.getGUIController().getMainWindow().getMainScene());
            document.registerObserver(mainController.getGUIController().getMainWindow().getMainScene().getMenuBar());
            document.registerObserver(mainController.getGUIController().getMainWindow().getMainScene().getToolBar());
            document.registerObserver(mainController.getGUIController().getMainWindow().getMainScene().getStatusBar());
            guiState.registerObserver(mainController.getGUIController().getMainWindow().getMainScene());
            guiState.registerObserver(mainController.getGUIController().getMainWindow().getMainScene().getMenuBar());
            guiState.registerObserver(mainController.getGUIController().getMainWindow().getMainScene().getToolBar());
            guiState.registerObserver(mainController.getGUIController().getMainWindow().getMainScene().getStatusBar());

            mainController.getGUIController().setSelectedTool(DrawTools.SELECT_TOOL);

            close();
        } catch (InputMismatchException exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(getWindow());
            alert.setContentText(exception.getMessage());

            alert.showAndWait();
        }
    }

    private void validateInputs() throws InputMismatchException {
        String title = titleTextField.getText();

        if (title.isEmpty()) {
            throw new InputMismatchException("Please specify a title.");
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