package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main;

import javafx.beans.binding.Bindings;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.MainController;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.Document;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.DocumentObserver;

import java.util.List;

public class MainScene extends Scene implements DocumentObserver {

    private MainController mainController;
    private MainWindow mainWindow;

    private BorderPane mainBorderPane;

    private VBox topPane;
    private MenuBar menuBar;
    private ToolBar toolBar;
    private Banner banner;

    private ArtBoard artBoard;
    private Group artBoardGroup;

    private StatusBar statusBar;

    public MainScene(MainController mainController, MainWindow mainWindow) {
        super(new BorderPane(), 1024, 768);

        this.mainController = mainController;
        this.mainWindow = mainWindow;

        mainBorderPane = new BorderPane();

        topPane = new VBox();

        menuBar = new MenuBar(this.mainController);
        toolBar = new ToolBar(this.mainController);

        showMenuBar(true);
        showToolBar(true);

        this.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.ALT)) {
                    showMenuBar(true);
                }
            }
        });

        mainBorderPane.setTop(topPane);

        artBoardGroup = new Group();
        StackPane stackPane = new StackPane(artBoardGroup);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setStyle("-fx-focus-color: transparent;");
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setContent(stackPane);

        stackPane.minWidthProperty().bind(Bindings.createDoubleBinding(() -> scrollPane.getViewportBounds().getWidth(), scrollPane.viewportBoundsProperty()));
        stackPane.minHeightProperty().bind(Bindings.createDoubleBinding(() -> scrollPane.getViewportBounds().getHeight(), scrollPane.viewportBoundsProperty()));

        mainBorderPane.setCenter(scrollPane);

        statusBar = new StatusBar(this.mainController);
        mainBorderPane.setBottom(statusBar);

        setRoot(mainBorderPane);
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }

    public void showMenuBar(boolean show) {
        if (show && !topPane.getChildren().contains(menuBar)) {
            topPane.getChildren().add(0, menuBar);
        } else {
            topPane.getChildren().remove(menuBar);
        }

        getMenuBar().selectViewMenuBarItem(show);
    }

    public ToolBar getToolBar() {
        return toolBar;
    }

    public void showToolBar(boolean show) {
        if (show && !topPane.getChildren().contains(topPane)) {
            topPane.getChildren().add(1, toolBar);
        } else {
            topPane.getChildren().remove(toolBar);
        }

        getMenuBar().selectViewToolBarItem(show);
    }

    public StatusBar getStatusBar() {
        return statusBar;
    }

    public void showStatusBar(boolean show) {
        if (show) {
            mainBorderPane.setBottom(statusBar);
        } else {
            mainBorderPane.setBottom(null);
        }

        getMenuBar().selectViewStatusBarItem(show);
    }

    public void setArtBoard(int width, int height) {
        if (artBoardGroup.getChildren().size() != 0) {
            clearArtBoard();
        }

        artBoard = new ArtBoard(mainController, width, height);

        artBoardGroup.getChildren().add(artBoard);

        getMenuBar().disableFileCloseItem(false);
    }

    public ArtBoard getArtBoard() {
        return artBoard;
    }

    public void clearArtBoard() {
        artBoardGroup.getChildren().clear();

        getMenuBar().disableFileCloseItem(true);
    }

    public void showBanner(String message) {
        if (banner != null) {
            hideBanner();
        }

        banner = new Banner(mainController, message);
        topPane.getChildren().add(2, banner);
    }

    public void hideBanner() {
        if (banner != null) {
            topPane.getChildren().remove(banner);
            banner = null;
        }
    }

    @Override
    public void update() {
        Document document = mainController.getDocumentController().getDocument();

        artBoardGroup.getChildren().clear();
        artBoardGroup.getChildren().add(artBoard);
        artBoardGroup.getChildren().addAll(document.getObjects());
    }
}