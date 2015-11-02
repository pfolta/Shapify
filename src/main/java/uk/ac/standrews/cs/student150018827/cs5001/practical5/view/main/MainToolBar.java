package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main;

import javafx.scene.control.ToolBar;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.GUIController;

public class MainToolBar extends ToolBar {

    private GUIController guiController;

    public MainToolBar(GUIController guiController) {
        super();

        this.guiController = guiController;
    }

}