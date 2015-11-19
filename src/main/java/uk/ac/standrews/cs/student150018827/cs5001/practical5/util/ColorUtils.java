package uk.ac.standrews.cs.student150018827.cs5001.practical5.util;

import javafx.scene.paint.Color;

public final class ColorUtils {

    private ColorUtils() {
    }

    public static String getHexColor(Color color) {
        return String.valueOf(color).replaceFirst("0x", "#").substring(0, 7);
    }

}