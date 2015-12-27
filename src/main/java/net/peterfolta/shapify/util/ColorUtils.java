package net.peterfolta.shapify.util;

import javafx.scene.paint.Color;

public final class ColorUtils {

    private ColorUtils() {
    }

    public static String getHexColor(Color color) {
        return String.valueOf(color).replaceFirst("0x", "#").substring(0, 7);
    }

}