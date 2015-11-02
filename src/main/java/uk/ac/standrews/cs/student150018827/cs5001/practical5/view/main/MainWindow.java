package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main;

import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.GUIController;

import java.io.File;

public class MainWindow {

    private GUIController guiController;
    private Stage mainStage;

    private MainScene mainScene;

    public MainWindow(GUIController guiController, Stage mainStage) {
        this.guiController = guiController;
        this.mainStage = mainStage;

        setup();
    }

    private void setup() {
        mainScene = new MainScene(guiController, this);

        mainStage.setTitle("Drawing Program");
        mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                guiController.exit();
                event.consume();
            }
        });

        mainStage.setScene(mainScene.getScene());
    }

    public void open() {
        mainStage.show();
        mainStage.requestFocus();
    }

    public void close() {
        mainStage.close();
    }

    public void openFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select File to Open");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Scalable Vector Graphic (*.svg)", "*.svg"),
            new FileChooser.ExtensionFilter("All Files (*.*)", "*.*")
        );

        File file = fileChooser.showOpenDialog(mainStage);

        if (file != null) {
            System.out.println("File selected: " + file);
        }
    }

    public void saveFile() {
        saveAsFile();
    }

    public void saveAsFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select File to Save To");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Scalable Vector Graphic (*.svg)", "*.svg"),
                new FileChooser.ExtensionFilter("All Files (*.*)", "*.*")
        );

        File file = fileChooser.showSaveDialog(mainStage);

        if (file != null) {
            System.out.println("File selected: " + file);
        }
    }

}