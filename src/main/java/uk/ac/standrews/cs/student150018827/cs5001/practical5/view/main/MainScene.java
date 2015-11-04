package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main;

import javafx.beans.binding.Bindings;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.MainController;

public class MainScene extends Scene {

    private MainController mainController;
    private MainWindow mainWindow;

    private BorderPane borderPane;

    private VBox topVBox;
    private MenuBar menuBar;
    private ToolBar toolBar;

    private ArtBoard artBoard;
    private Group artBoardGroup;

    private StatusBar statusBar;

    public MainScene(MainController mainController, MainWindow mainWindow) {
        super(new BorderPane(), 1024, 768);

        this.mainController = mainController;
        this.mainWindow = mainWindow;

        borderPane = new BorderPane();

        topVBox = new VBox();

        menuBar = new MenuBar(this.mainController);
        toolBar = new ToolBar(this.mainController);

        topVBox.getChildren().addAll(menuBar, toolBar);

        borderPane.setTop(topVBox);

        artBoardGroup = new Group();
        StackPane stackPane = new StackPane(artBoardGroup);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setStyle("-fx-focus-color: transparent;");
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setContent(stackPane);

        stackPane.minWidthProperty().bind(Bindings.createDoubleBinding(() -> scrollPane.getViewportBounds().getWidth(), scrollPane.viewportBoundsProperty()));
        stackPane.minHeightProperty().bind(Bindings.createDoubleBinding(() -> scrollPane.getViewportBounds().getHeight(), scrollPane.viewportBoundsProperty()));

        borderPane.setCenter(scrollPane);

        statusBar = new StatusBar(this.mainController);
        borderPane.setBottom(statusBar);

        setRoot(borderPane);
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }

    public ToolBar getToolBar() {
        return toolBar;
    }

    public void showToolBar(boolean show) {
        if (show && !topVBox.getChildren().contains(toolBar)) {
            topVBox.getChildren().add(toolBar);
        } else {
            topVBox.getChildren().remove(toolBar);
        }
    }

    public StatusBar getStatusBar() {
        return statusBar;
    }

    public void showStatusBar(boolean show) {
        if (show) {
            borderPane.setBottom(statusBar);
        } else {
            borderPane.setBottom(null);
        }
    }

    public void setArtBoard(int width, int height) {
        if (artBoardGroup.getChildren().size() != 0) {
            clearArtBoard();
        }

        artBoard = new ArtBoard(mainController, this, width, height);

        artBoardGroup.getChildren().add(artBoard);

        getMenuBar().disableFileCloseItem(false);
    }

    public void clearArtBoard() {
        artBoardGroup.getChildren().remove(artBoard);

        getMenuBar().disableFileCloseItem(true);
    }

}