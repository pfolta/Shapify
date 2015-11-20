package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main;

import javafx.beans.binding.Bindings;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.MainController;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.Document;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.GUIState;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.Observer;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.DrawTools;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.eventhandlers.KeyEventHandler;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.focusoutline.FocusOutline;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.focusoutline.ResizeAnchor;

public class MainScene extends Scene implements Observer {

    private MainController mainController;
    private MainWindow mainWindow;

    private BorderPane mainBorderPane;

    private VBox topPane;
    private MenuBar menuBar;
    private ToolBar toolBar;
    private Banner banner;

    private ArtBoard artBoard;
    private Group artBoardGroup;
    private Group artBoardZoomGroup;

    private StatusBar statusBar;

    public MainScene(MainController mainController, MainWindow mainWindow) {
        super(new BorderPane(), 1024, 768);

        getStylesheets().add(ClassLoader.getSystemResource("css/general.css").toExternalForm());
        getStylesheets().add(ClassLoader.getSystemResource("css/mainscene.css").toExternalForm());

        this.mainController = mainController;
        this.mainWindow = mainWindow;

        mainBorderPane = new BorderPane();

        topPane = new VBox();

        menuBar = new MenuBar(this.mainController);
        toolBar = new ToolBar(this.mainController);

        showMenuBar(true);
        showToolBar(true);

        mainBorderPane.setTop(topPane);

        artBoardGroup = new Group();
        artBoardGroup.getStyleClass().add("no-focus-outline");

        artBoardZoomGroup = new Group();
        artBoardZoomGroup.getStyleClass().add("no-focus-outline");
        artBoardZoomGroup.getChildren().add(artBoardGroup);

        StackPane stackPane = new StackPane(artBoardZoomGroup);
        stackPane.getStyleClass().add("no-focus-outline");

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.getStyleClass().add("no-focus-outline");
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setContent(stackPane);
        scrollPane.setOnMouseMoved(event -> {
            if (artBoard != null) {
                try {
                    artBoard.fireEvent(event);
                } catch (StackOverflowError error) {
                }
            }
        });
        scrollPane.setOnMouseDragged(event -> {
            if (artBoard != null) {
                try {
                    artBoard.fireEvent(event);
                } catch (StackOverflowError error) {
                }
            }
        });

        stackPane.minWidthProperty().bind(Bindings.createDoubleBinding(() -> scrollPane.getViewportBounds().getWidth(), scrollPane.viewportBoundsProperty()));
        stackPane.minHeightProperty().bind(Bindings.createDoubleBinding(() -> scrollPane.getViewportBounds().getHeight(), scrollPane.viewportBoundsProperty()));

        mainBorderPane.setCenter(scrollPane);

        statusBar = new StatusBar(this.mainController);
        mainBorderPane.setBottom(statusBar);

        // Register Key Event Handler
        KeyEventHandler keyEventHandler = new KeyEventHandler(mainController);
        setOnKeyPressed(keyEventHandler.getKeyPressedEventHandler());

        setRoot(mainBorderPane);
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }

    public void showMenuBar(boolean show) {
        if (show) {
            if (!topPane.getChildren().contains(menuBar)) {
                topPane.getChildren().add(0, menuBar);
            }
        } else {
            topPane.getChildren().remove(menuBar);
        }

        getMenuBar().selectViewMenuBarItem(show);
    }

    public void showToolBar(boolean show) {
        if (show) {
            if (!topPane.getChildren().contains(topPane)) {
                topPane.getChildren().add(1, toolBar);
            }
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
    }

    public ArtBoard getArtBoard() {
        return artBoard;
    }

    public void reset() {
        clearArtBoard();
        mainWindow.setTitle(null);
    }

    private void clearArtBoard() {
        artBoard = null;
        artBoardGroup.getChildren().clear();
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

    public void activateControls(boolean activate) {
        menuBar.activateControls(activate);
        toolBar.activateControls(activate);
    }

    @Override
    public void update() {
        Document document = mainController.getDocumentController().getDocument();
        GUIState guiState = mainController.getGUIController().getGuiState();

        mainWindow.setTitle(document.getTitle());

        if (artBoard != null) {
            artBoardGroup.getChildren().clear();
            artBoardGroup.getChildren().add(artBoard);
            artBoardGroup.getChildren().addAll(document.getObjects());

            if (guiState.getSelectedObject() != null) {
                Node selectedObject = guiState.getSelectedObject();

                // Draw Focus Outline
                FocusOutline focusOutline = guiState.getFocusOutline();
                Rectangle focusRectangle = focusOutline.getFocusRectangle();

                artBoardGroup.getChildren().add(focusRectangle);

                // Draw Focus Outline Resize Anchors
                for (ResizeAnchor resizeAnchor : focusOutline.getResizeAnchors()) {
                    artBoardGroup.getChildren().add(resizeAnchor);
                }

                // Draw Focus Outline Rotate Anchor
                artBoardGroup.getChildren().add(focusOutline.getRotateAnchor());

                // Set Color of Object
                if (selectedObject instanceof Rectangle) {
                    ((Rectangle) selectedObject).setFill(guiState.getFillColor());
                    ((Rectangle) selectedObject).setStroke(guiState.getStrokeColor());
                    ((Rectangle) selectedObject).setStrokeWidth(guiState.getStrokeWidth().getStrokeWidth());
                }

                if (selectedObject instanceof Ellipse) {
                    ((Ellipse) selectedObject).setFill(guiState.getFillColor());
                    ((Ellipse) selectedObject).setStroke(guiState.getStrokeColor());
                    ((Ellipse) selectedObject).setStrokeWidth(guiState.getStrokeWidth().getStrokeWidth());
                }

                if (selectedObject instanceof Line) {
                    ((Line) selectedObject).setStroke(guiState.getStrokeColor());
                    ((Line) selectedObject).setStrokeWidth(guiState.getStrokeWidth().getStrokeWidth());
                }
            }

            // Set Zoom Level
            setArtBoardZoomLevel(guiState.getZoomLevel());

            menuBar.objectSelected(guiState.getSelectedObject() != null);
            toolBar.objectSelected(guiState.getSelectedObject() != null);
        }
    }

    public ToolBar getToolBar() {
        return toolBar;
    }

    private void setArtBoardZoomLevel(double zoomLevel) {
        artBoardGroup.setScaleX(zoomLevel);
        artBoardGroup.setScaleY(zoomLevel);
    }

}